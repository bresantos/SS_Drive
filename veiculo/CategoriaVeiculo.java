package com.locadora.veiculo;

public enum CategoriaVeiculo {
    ECONOMICO(80.00),
    INTERMEDIARIO(120.00),
    EXECUTIVO(200.00);

    private final double valorDiaria;

    CategoriaVeiculo(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }
}
