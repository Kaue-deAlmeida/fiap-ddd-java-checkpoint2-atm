package br.com.fiap.bank.atm;

import br.com.fiap.bank.atm.application.AutorizacaoService;
import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.model.Cliente;
import br.com.fiap.bank.atm.model.Conta;
import br.com.fiap.bank.atm.model.ContaCorrente;
import br.com.fiap.bank.atm.model.ContaAcesso;
import br.com.fiap.bank.atm.model.Dinheiro;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BEM-VINDO AO FIAP BANK ===");
        System.out.println("Para iniciar, crie a sua conta corrente.");

        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Cadastre uma senha de acesso: ");
        String senhaCadastrada = scanner.nextLine();

        System.out.print("Digite o saldo inicial de abertura: R$ ");
        BigDecimal saldoInicialInput = new BigDecimal(scanner.nextLine());

        Cliente cliente = new Cliente(nome);
        ContaAcesso acesso = new ContaAcesso(senhaCadastrada);
        Dinheiro saldoInicial = new Dinheiro(saldoInicialInput);

        Conta contaDoUsuario = new ContaCorrente(cliente, acesso, saldoInicial);

        ContaService contaService = new ContaService(contaDoUsuario);
        AutorizacaoService autorizacaoService = new AutorizacaoService(contaDoUsuario);

        System.out.println("\nConta criada com sucesso! Redirecionando para o caixa eletrônico...\n");

        TerminalBancarioController terminal = new TerminalBancarioController(contaService, autorizacaoService);

        terminal.exibirMenuPrincipal();
    }
}