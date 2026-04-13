package com.locadora.veiculo;

public class Veiculo {
    private String id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private CategoriaVeiculo categoria;
    private StatusVeiculo status;

    public Veiculo(String id, String placa, String marca, String modelo, int ano, CategoriaVeiculo categoria) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.categoria = categoria;
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public String getId() { return id; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public CategoriaVeiculo getCategoria() { return categoria; }
    public StatusVeiculo getStatus() { return status; }

    public boolean estaDisponivel() {
        return status == StatusVeiculo.DISPONIVEL;
    }

    public void marcarComoAlugado() {
        this.status = StatusVeiculo.ALUGADO;
    }

    public void marcarComoDisponivel() {
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public double getValorDiaria() {
        return categoria.getValorDiaria();
    }
}
