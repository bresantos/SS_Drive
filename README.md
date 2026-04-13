# 💜 SS_Drive

> Sistema de Locadora de Veículos

SS_Drive é uma plataforma para gerenciamento de locação de veículos, desenvolvida com Domain-Driven Design (DDD). Permite controlar clientes, veículos e todo o processo de aluguel de forma organizada e segura.

---

## 👥 Autores

**Projeto Acadêmico — FIAP | Domain Driven Design (2026)**

Brenda Santos — RM561258 | Lucas Santana — RM566261

---

## 🎯 Problema

Gerenciar uma locadora envolve múltiplas informações: clientes, documentos, veículos e locações. Sem um sistema estruturado, isso gera erros de disponibilidade, falhas em cobranças e perda de controle operacional. O SS_Drive resolve esse cenário com uma modelagem clara e automatizada.

---

## 🚀 Funcionalidades

### 👤 Clientes
Cadastro completo de clientes | Validação de CNH | Armazenamento de endereço

### 🚗 Veículos
Cadastro com placa, marca, modelo e ano | Categorias: Econômico (R$ 80/dia), Intermediário (R$ 120/dia), Executivo (R$ 200/dia) | Controle de status: Disponível / Alugado

### 📄 Locações
Cálculo automático de dias | Cálculo de valor base | Multa por atraso: R$ 50/dia | Valor total da locação

---

## 🏗️ Arquitetura
```
SS_Drive/
├─── com/
│    ├─── locadora/
│    │    ├─── Main.java
│    │    ├─── Testador.java
│    │    ├─── cliente/
│    │    │    ├─── Cliente.java
│    │    │    ├─── CNH.java
│    │    │    └─── Endereco.java
│    │    ├─── veiculo/
│    │    │    ├─── Veiculo.java
│    │    │    ├─── CategoriaVeiculo.java
│    │    │    └─── StatusVeiculo.java
│    │    └─── locacao/
│    │         └─── Locacao.java
```
---

## 💡 Decisões Técnicas

Java puro → simplicidade e foco na lógica | DDD → organização por domínio | Value Objects → CNH e Endereço imutáveis | Enums → categorias e status definidos | Encapsulamento → regras dentro das entidades

---

## 📂 Modelagem

### Cliente (Cliente.java)

**Atributos:** id, nome, cpf, telefone, email, endereço, cnh

**Métodos:** `atualizarNome()`, `atualizarTelefone()`, `atualizarEmail()`, `atualizarEndereco()`, `temCnhValida()`

### CNH (CNH.java)

**Atributos:** número, data de vencimento

**Método:** `estaValida()`

### Endereço (Endereco.java)

**Atributos:** rua, número, bairro, cidade, CEP

### Veículo (Veiculo.java)

**Atributos:** id, placa, marca, modelo, ano, categoria, status

**Métodos:** `estaDisponivel()`, `marcarComoAlugado()`, `marcarComoDisponivel()`, `getValorDiaria()`

### CategoriaVeiculo (Enum)

ECONOMICO — R$ 80,00 | INTERMEDIARIO — R$ 120,00 | EXECUTIVO — R$ 200,00

### StatusVeiculo (Enum)

DISPONIVEL | ALUGADO

### Locação (Locacao.java)

**Atributos:** id, clienteId, veiculoId, datas

**Métodos:** `calcularDiasLocacao()`, `calcularDiasAtraso()`, `calcularValorBase()`, `calcularMulta()`, `calcularValorTotal()`, `registrarDevolucao()`

---

## 🚀 Execução

### Pré-requisitos
JDK 8+ | IDE Java ou terminal

### Rodar projeto
`cd locadora` → `javac Main.java` → `java Main`

---

## 🛠️ Tecnologias

Java 8+ | Java Time API | Domain-Driven Design

---

## 📈 Próximos Passos

**Curto prazo:** Interface gráfica | Persistência em banco | Validação de CPF

**Médio prazo:** API REST | Sistema de reservas | Notificações

**Longo prazo:** Aplicação web | App mobile | Integração com pagamentos
