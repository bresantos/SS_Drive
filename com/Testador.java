package com.locadora;

import com.locadora.cliente.Cliente;
import com.locadora.cliente.Endereco;
import com.locadora.cliente.CNH;
import com.locadora.veiculo.Veiculo;
import com.locadora.veiculo.CategoriaVeiculo;
import com.locadora.veiculo.StatusVeiculo;
import com.locadora.locacao.Locacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Testador {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Locacao> locacoes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== TESTES DAS REGRAS DE NEGOCIO ===\n");

        cadastrarDadosIniciais();

        testarCpfDuplicado();
        testarPlacaDuplicada();
        testarLocacaoVeiculoJaAlugado();
        testarLocacaoCnhVencida();
    }

    private static void cadastrarDadosIniciais() {
        Endereco end1 = new Endereco("Rua Flores", "123", "Centro", "Sao Paulo", "01001-000");
        CNH cnhValida = new CNH("12345678900", LocalDate.now().plusYears(2));
        Cliente c1 = new Cliente("C001", "Joao Silva", "111.111.111-11", "1199999-0001", "joao@email.com", end1, cnhValida);
        clientes.add(c1);

        Endereco end2 = new Endereco("Av. Brasil", "456", "Jardim", "Rio de Janeiro", "20000-000");
        CNH cnhVencida = new CNH("98765432100", LocalDate.now().minusDays(1));
        Cliente c2 = new Cliente("C002", "Maria Santos", "222.222.222-22", "2199999-0002", "maria@email.com", end2, cnhVencida);
        clientes.add(c2);

        veiculos.add(new Veiculo("V001", "ABC-1234", "Volkswagen", "Gol", 2023, CategoriaVeiculo.ECONOMICO));
        veiculos.add(new Veiculo("V002", "DEF-5678", "Toyota", "Corolla", 2024, CategoriaVeiculo.INTERMEDIARIO));
    }

    private static void testarCpfDuplicado() {
        System.out.println("--- TESTE: CPF Duplicado (R3) ---");
        Endereco end = new Endereco("Rua X", "1", "Bairro", "Cidade", "00000-000");
        CNH cnh = new CNH("55555555555", LocalDate.now().plusYears(1));

        String cpfExistente = "111.111.111-11";

        boolean cpfJaExiste = clientes.stream().anyMatch(c -> c.getCpf().equals(cpfExistente));

        if (cpfJaExiste) {
            System.out.println("[BLOQUEADO] CPF " + cpfExistente + " ja cadastrado!");
        } else {
            System.out.println("[OK] CPF disponivel");
        }
        System.out.println();
    }

    private static void testarPlacaDuplicada() {
        System.out.println("--- TESTE: Placa Duplicada (R4) ---");
        String placaExistente = "ABC-1234";

        boolean placaJaExiste = veiculos.stream().anyMatch(v -> v.getPlaca().equals(placaExistente));

        if (placaJaExiste) {
            System.out.println("[BLOQUEADO] Placa " + placaExistente + " ja cadastrada!");
        } else {
            System.out.println("[OK] Placa disponivel");
        }
        System.out.println();
    }

    private static void testarLocacaoVeiculoJaAlugado() {
        System.out.println("--- TESTE: Locar Veiculo Ja Alugado (R1) ---");

        Cliente cliente = clientes.get(0);
        Veiculo veiculo = veiculos.get(0);

        Locacao loc1 = new Locacao("L001", cliente.getId(), veiculo.getId(), veiculo.getCategoria(), LocalDate.now(), LocalDate.now().plusDays(5));
        locacoes.add(loc1);
        veiculo.marcarComoAlugado();

        System.out.println("Veiculo " + veiculo.getPlaca() + " agora esta: " + veiculo.getStatus());

        if (!veiculo.estaDisponivel()) {
            System.out.println("[BLOQUEADO] Veiculo ja esta alugado! Nao pode ser locado novamente.");
        } else {
            System.out.println("[OK] Veiculo disponivel para locacao");
        }
        System.out.println();
    }

    private static void testarLocacaoCnhVencida() {
        System.out.println("--- TESTE: Locacao com CNH Vencida (R2) ---");

        Cliente clienteComCnhVencida = clientes.get(1);

        System.out.println("Cliente: " + clienteComCnhVencida.getNome());
        System.out.println("CNH Valida: " + clienteComCnhVencida.temCnhValida());

        if (!clienteComCnhVencida.temCnhValida()) {
            System.out.println("[BLOQUEADO] CNH esta vencida! Nao pode alugar.");
        } else {
            System.out.println("[OK] CNH valida para locacao");
        }
        System.out.println();
    }
}
