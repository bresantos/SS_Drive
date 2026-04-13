# 💜 SS_Drive

> **Sistema de Locadora de Veículos**

SS_Drive é uma plataforma para gerenciamento de locação de veículos, desenvolvida com Domain-Driven Design (DDD). Permite controlar clientes, veículos e todo o processo de aluguel.

---

## 🎯 O Problema

Gestionar uma locadora de veículos envolve lidar com múltiplas informações: clientes, documentos, veículos em diferentes categorias e controle de aluguéis. Sem um sistema estruturado, esse processo pode gerar erros, perda de dados e falta de controle sobre a disponibilidade dos veículos.

SS_Drive resolve isso.

---

## 🚀 Funcionalidades Principais

### ✅ Cadastro de Clientes - **Cliente**: Gerenciamento completo com dados pessoais | **CNH**: Validação de habilitação do cliente | **Endereço**: Informações de contato e localização

### ✅ Gestão de Veículos - **Veículo**: Cadastro com placa, marca, modelo e ano | **Categorias**: ECONOMICO (R$ 80/dia), INTERMEDIARIO (R$ 120/dia), EXECUTIVO (R$ 200/dia) | **Status**: Controle de disponibilidade (DISPONIVEL / ALUGADO)

### ✅ Controle de Locações - **Duração**: Cálculo automático de dias de locação | **Atrasos**: Detecção e cobrança de multas (R$ 50/dia) | **Valores**: Cálculo de valor base, multas e total

---

## 🏗️ Arquitetura do Projeto
locadora/ 
├── Main.java # Ponto de entrada da aplicação 
├── Testador.java # Classe de testes 
│ ├── cliente/ 
│ ├── Cliente.java # Entidade principal do cliente 
│ ├── CNH.java # Value Object - CNH 
│ └── Endereco.java # Value Object - Endereço 
│ ├── veiculo/ 
│ ├── Veiculo.java # Entidade principal do veículo 
│ ├── CategoriaVeiculo.java # Enum - Categorias com valores 
│ └── StatusVeiculo.java # Enum - Status do veículo 
│ └── locacao/ 
│ └── Locacao.java # Entidade - Controle de aluguéis


### 💡 Decisões Técnicas

**Java Puro**: Desenvolvimento simples, sem dependências externas | **Domain-Driven Design**: Estrutura organizada por domínios do negócio | **Value Objects**: CNH e Endereço como objetos imutáveis | **Enums**: Categorias e status com valores pré-definidos | **Métodos de Cálculo**: Lógica de negócio encapsulada nas entidades

---

## 📂 Classes e Métodos

### Cliente - Arquivo: `Cliente.java`

**Atributos:** id, nome, cpf, telefone, email, endereco (Value Object), cnh (Value Object)

**Métodos:** `atualizarNome()` - Atualiza o nome do cliente | `atualizarTelefone()` - Atualiza o telefone | `atualizarEmail()` - Atualiza o e-mail | `atualizarEndereco()` - Atualiza o endereço | `temCnhValida()` - Verifica se a CNH está válida

### CNH (Value Object) - Arquivo: `CNH.java`

**Atributos:** numero, dataVencimento

**Métodos:** `estaValida()` - Retorna true se não estiver vencida

### Endereco (Value Object) - Arquivo: `Endereco.java`

**Atributos:** rua, numero, bairro, cidade, cep

### Veiculo - Arquivo: `Veiculo.java`

**Atributos:** id, placa, marca, modelo, ano, categoria (enum), status (enum)

**Métodos:** `estaDisponivel()` - Verifica se está disponível | `marcarComoAlugado()` - Altera status para ALUGADO | `marcarComoDisponivel()` - Altera status para DISPONIVEL | `getValorDiaria()` - Retorna valor baseado na categoria

### CategoriaVeiculo (Enum) - Arquivo: `CategoriaVeiculo.java`

ECONOMICO - R$ 80,00 | INTERMEDIARIO - R$ 120,00 | EXECUTIVO - R$ 200,00

### StatusVeiculo (Enum) - Arquivo: `StatusVeiculo.java`

Valores: DISPONIVEL, ALUGADO

### Locacao - Arquivo: `Locacao.java`

**Atributos:** id, clienteId, veiculoId, categoria, dataRetirada, dataPrevistaDevolucao, dataRealDevolucao

**Métodos:** `calcularDiasLocacao()` - Dias entre retirada e devolução | `calcularDiasAtraso()` - Dias de atraso na devolução | `calcularValorBase()` - Diária × dias de locação | `calcularMulta()` - R$ 50,00 × dias de atraso | `calcularValorTotal()` - Valor base + multa | `registrarDevolucao()` - Registra data real de devolução

---

## 🚀 Como Rodar

### Pré-requisitos: JDK 8+ instalado | IDE Java (VS Code, IntelliJ, Eclipse) ou terminal

### Compilação: `cd locadora` → `javac Main.java`

### Execução: `java Main`

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia   | Versão | Propósito |
|--------------|--------|----------|
| Java         | 8+     | Linguagem principal |
| Java Time API| -      | Manipulação de datas |
| DDD          | -      | Arquitetura |

---

## 📈 Próximos Passos

**Curto Prazo:** Interface gráfica (Swing/JavaFX) | Persistência em banco de dados | Validação de CPF

**Médio Prazo:** API REST | Sistema de reservas | Notificações de vencimento de CNH

**Longo Prazo:** Aplicação web | App mobile | Integração com sistemas de pagamento

---

## 👥 Autores

**Projeto Acadêmico FIAP - Domain Driven Design 2026** 
- Desenvolvido por: Brenda Santos e Lucas Santana

---

**Feito com 💜 por SS_Drive Team**
