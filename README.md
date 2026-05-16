# FIAP Bank ATM - Checkpoint 2

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

Protótipo de um terminal de Autoatendimento (Caixa Eletrônico) desenvolvido como parte do **Checkpoint 2** da disciplina de **Domain Driven Design - Java** no curso de Engenharia de Software da FIAP.

Esta versão (**Beta**) representa a evolução do projeto, abandonando o modelo procedural em favor de uma arquitetura rica orientada a objetos (OO) e guiada pelo domínio (DDD).

## 📌 O Projeto

O **FIAP Bank ATM** foi completamente refatorado para isolar as regras de negócio em camadas bem definidas (`model`, `application`, `presentation`), garantindo encapsulamento rígido, forte tipagem sem tipos primitivos e persistência em memória via console.

### Funcionalidades
- **Criação de Conta em Tempo de Execução:** Captura interativa do nome do cliente, definição de senha do cartão e valor de depósito inicial para abertura da conta corrente.
- **Autenticação Segura com Controle de Estado:** Validação de acesso através do Value Object `ContaAcesso`, com limitação de 3 tentativas consecutivas de erro antes do bloqueio permanente da sessão.
- **Padrão Template Method de Saque/Depósito:** Fluxo de movimentação padronizado e selado na classe abstrata mãe `Conta`, aplicando de forma polimórfica ganchos de tarifações específicas para as subclasses.
- **Histórico Completo de Movimentações (Extrato):** Registro detalhado e imutável de todas as transações, incluindo depósitos, saques, rendimentos e tarifas bancárias deduzidas automaticamente.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Paradigma:** Orientação a Objetos & Domain-Driven Design (DDD)
- **Estrutura de Controle:** Coleções Imutáveis, Wrappers Java e `BigDecimal` para precisão monetária
- **Ferramentas:** Git & GitHub

## 🚀 Como Executar

1. Certifique-se de ter o JDK (Java Development Kit) 17 ou superior instalado em sua máquina.
2. Clone o repositório:
```bash
git clone https://github.com/Kaue-deAlmeida/fiap-ddd-java-checkpoint2-atm.git
```
Navegue até a pasta do projeto:

```bash
cd fiap-ddd-java-checkpoint2-atm
Compile todos os pacotes e arquivos do projeto:
```
   bash
   javac -d bin src/br/fiap/bank/atm/model/*.java src/br/fiap/bank/atm/application/*.java src/br/fiap/bank/atm/presentation/*.java src/br/fiap/bank/atm/Main.java
   Execute a aplicação a partir do ponto de entrada (Main):

```bash
java -cp bin br.fiap.bank.atm.Main
````
Desenvolvido por: Kauê de Almeida

Turma: 2ESPH - Engenharia de Software FIAP

Professor: Eduardo dos Santos Ramos