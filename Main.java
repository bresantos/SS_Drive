package com.locadora;

import com.locadora.cliente.Cliente;
import com.locadora.cliente.Endereco;
import com.locadora.cliente.CNH;
import com.locadora.veiculo.Veiculo;
import com.locadora.veiculo.CategoriaVeiculo;
import com.locadora.locacao.Locacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Locacao> locacoes = new ArrayList<>();

    public static void main(String[] args) {
        cadastrarClientes();
        cadastrarVeiculos();

        System.out.println("=== LOCACOES ===\n");
        realizarLocacao("C001", "V001", 5);
        realizarLocacao("C002", "V002", 3);

        System.out.println("\n=== SITUACAO DOS VEICULOS ===");
        for (Veiculo v : veiculos) {
            System.out.println(v.getPlaca() + ": " + v.getStatus());
        }

        System.out.println("\n=== DEVOLUCAO COM ATRASO ===");
        devolverVeiculo("L001", 7);

        Locacao loc1 = locacoes.get(0);
        System.out.println("Dias atraso: " + loc1.calcularDiasAtraso());
        System.out.println("Multa: R$ " + loc1.calcularMulta());
        System.out.println("Total a pagar: R$ " + loc1.calcularValorTotal());
    }

    private static void cadastrarClientes() {
        System.out.println("=== CADASTRANDO CLIENTES ===");
        
        Endereco end1 = new Endereco("Rua Flores", "123", "Centro", "Sao Paulo", "01001-000");
        CNH cnh1 = new CNH("12345678900", LocalDate.now().plusYears(2));
        Cliente c1 = new Cliente("C001", "Joao Silva", "111.111.111-11", "1199999-0001", "joao@email.com", end1, cnh1);
        clientes.add(c1);
        System.out.println("Cliente: " + c1.getNome());

        Endereco end2 = new Endereco("Av. Brasil", "456", "Jardim", "Rio de Janeiro", "20000-000");
        CNH cnh2 = new CNH("98765432100", LocalDate.now().plusYears(1));
        Cliente c2 = new Cliente("C002", "Maria Santos", "222.222.222-22", "2199999-0002", "maria@email.com", end2, cnh2);
        clientes.add(c2);
        System.out.println("Cliente: " + c2.getNome());
    }

    private static void cadastrarVeiculos() {
        System.out.println("\n=== CADASTRANDO VEICULOS ===");
        
        Veiculo v1 = new Veiculo("V001", "ABC-1234", "Volkswagen", "Gol", 2023, CategoriaVeiculo.ECONOMICO);
        veiculos.add(v1);
        System.out.println(v1.getMarca() + " " + v1.getModelo() + " - " + v1.getPlaca() + " - R$ " + v1.getValorDiaria() + "/dia");

        Veiculo v2 = new Veiculo("V002", "DEF-5678", "Toyota", "Corolla", 2024, CategoriaVeiculo.INTERMEDIARIO);
        veiculos.add(v2);
        System.out.println(v2.getMarca() + " " + v2.getModelo() + " - " + v2.getPlaca() + " - R$ " + v2.getValorDiaria() + "/dia");

        Veiculo v3 = new Veiculo("V003", "GHI-9012", "BMW", "Serie 3", 2024, CategoriaVeiculo.EXECUTIVO);
        veiculos.add(v3);
        System.out.println(v3.getMarca() + " " + v3.getModelo() + " - " + v3.getPlaca() + " - R$ " + v3.getValorDiaria() + "/dia");
    }

    private static void realizarLocacao(String clienteId, String veiculoId, int dias) {
        Cliente cliente = buscarCliente(clienteId);
        Veiculo veiculo = buscarVeiculo(veiculoId);

        if (cliente == null || veiculo == null) {
            System.out.println("Erro: Cliente ou veiculo nao encontrado");
            return;
        }

        if (!veiculo.estaDisponivel()) {
            System.out.println("Erro: Veiculo ja alugado (R1)");
            return;
        }

        if (!cliente.temCnhValida()) {
            System.out.println("Erro: CNH vencida (R2)");
            return;
        }

        String locId = "L" + (locacoes.size() + 1);
        LocalDate retirada = LocalDate.now();
        LocalDate devolucao = retirada.plusDays(dias);

        Locacao locacao = new Locacao(locId, clienteId, veiculoId, veiculo.getCategoria(), retirada, devolucao);
        locacoes.add(locacao);
        veiculo.marcarComoAlugado();

        System.out.println("\nLocacao: " + locId);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veiculo: " + veiculo.getMarca() + " " + veiculo.getModelo());
        System.out.println("Periodo: " + dias + " dias");
        System.out.println("Valor base: R$ " + locacao.calcularValorBase());
    }

    private static void devolverVeiculo(String locacaoId, int diasAtraso) {
        Locacao locacao = buscarLocacao(locacaoId);
        if (locacao == null) {
            System.out.println("Locacao nao encontrada");
            return;
        }

        LocalDate dataDevolucao = locacao.getDataPrevistaDevolucao().plusDays(diasAtraso);
        locacao.registrarDevolucao(dataDevolucao);

        Veiculo veiculo = buscarVeiculo(locacao.getVeiculoId());
        if (veiculo != null) {
            veiculo.marcarComoDisponivel();
        }

        System.out.println("Data devolucao: " + dataDevolucao);
        System.out.println("Dias de atraso: " + locacao.calcularDiasAtraso());
        System.out.println("Multa (R$50/dia): R$ " + locacao.calcularMulta());
    }

    private static Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    private static Veiculo buscarVeiculo(String id) {
        for (Veiculo v : veiculos) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }

    private static Locacao buscarLocacao(String id) {
        for (Locacao l : locacoes) {
            if (l.getId().equals(id)) return l;
        }
        return null;
    }
}
