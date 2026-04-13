package com.locadora.cliente;

public class Cliente {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
    private CNH cnh;

    public Cliente(String id, String nome, String cpf, String telefone, String email, Endereco endereco, CNH cnh) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cnh = cnh;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public Endereco getEndereco() { return endereco; }
    public CNH getCnh() { return cnh; }

    public void atualizarNome(String nome) {
        this.nome = nome;
    }

    public void atualizarTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void atualizarEmail(String email) {
        this.email = email;
    }

    public void atualizarEndereco(Endereco novoEndereco) {
        this.endereco = novoEndereco;
    }

    public boolean temCnhValida() {
        return cnh.estaValida();
    }
}
