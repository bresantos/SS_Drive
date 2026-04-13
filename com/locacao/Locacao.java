package com.locadora.locacao;

import com.locadora.veiculo.CategoriaVeiculo;
import java.time.LocalDate;

public class Locacao {
    private String id;
    private String clienteId;
    private String veiculoId;
    private CategoriaVeiculo categoria;
    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;

    public Locacao(String id, String clienteId, String veiculoId, CategoriaVeiculo categoria, LocalDate dataRetirada, LocalDate dataPrevistaDevolucao) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.categoria = categoria;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public String getId() { return id; }
    public String getVeiculoId() { return veiculoId; }
    public CategoriaVeiculo getCategoria() { return categoria; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataRealDevolucao() { return dataRealDevolucao; }

    public long calcularDiasLocacao() {
        LocalDate fim = (dataRealDevolucao != null) ? dataRealDevolucao : LocalDate.now();
        return java.time.temporal.ChronoUnit.DAYS.between(dataRetirada, fim);
    }

    public long calcularDiasAtraso() {
        if (dataRealDevolucao == null) return 0;
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
        return dias > 0 ? dias : 0;
    }

    public double calcularValorBase() {
        return categoria.getValorDiaria() * calcularDiasLocacao();
    }

    public double calcularMulta() {
        return calcularDiasAtraso() * 50.0;
    }

    public double calcularValorTotal() {
        return calcularValorBase() + calcularMulta();
    }

    public void registrarDevolucao(LocalDate data) {
        this.dataRealDevolucao = data;
    }
}
