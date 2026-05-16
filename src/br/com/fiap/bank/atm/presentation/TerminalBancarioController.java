package br.com.fiap.bank.atm.presentation;

import br.com.fiap.bank.atm.application.AutorizacaoService;
import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.model.Dinheiro;
import br.com.fiap.bank.atm.model.Movimentacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TerminalBancarioController {
    private final ContaService contaService;
    private final AutorizacaoService autorizacaoService;
    private final Scanner scanner;

    public TerminalBancarioController(ContaService contaService, AutorizacaoService autorizacaoService) {
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() {
        if (!autenticarUsuario()) {
            System.out.println("Acesso negado. Conta temporariamente bloqueada.");
            return;
        }

        Integer opcao = 0;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("[1] Consultar Saldo");
            System.out.println("[2] Fazer Depósito");
            System.out.println("[3] Fazer Saque");
            System.out.println("[4] Histórico de Movimentações");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> exibirSaldo();
                    case 2 -> realizarDeposito();
                    case 3 -> realizarSaque();
                    case 4 -> exibirMovimentacoes();
                    case 5 -> System.out.println("Sessão finalizada.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
            } catch (Exception e) {
                System.out.println("Erro na operação: " + e.getMessage());
            }
        } while (!opcao.equals(5));
    }

    private Boolean autenticarUsuario() {
        System.out.println("=== FIAP BANK - IDENTIFICAÇÃO ===");
        for (int i = 0; i < 3; i++) {
            System.out.print("Digite a senha do cartão: ");
            String senha = scanner.nextLine();
            if (autorizacaoService.autorizar(senha)) {
                return Boolean.TRUE;
            }
            System.out.println("Senha incorreta.");
        }
        return Boolean.FALSE;
    }

    public void exibirSaldo() {
        System.out.println("\n--- SALDO EM CONTA ---");
        System.out.println("Disponível: " + contaService.obterSaldo());
    }

    public void realizarDeposito() {
        System.out.print("\nValor do depósito: R$ ");
        try {
            BigDecimal valorInput = new BigDecimal(scanner.nextLine());
            contaService.realizarDeposito(new Dinheiro(valorInput));
            System.out.println("Depósito processado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao depositar: " + e.getMessage());
        }
    }

    public void realizarSaque() {
        System.out.print("\nValor do saque: R$ ");
        try {
            BigDecimal valorInput = new BigDecimal(scanner.nextLine());
            contaService.realizarSaque(new Dinheiro(valorInput));
            System.out.println("Retire o dinheiro no local indicado.");
        } catch (Exception e) {
            System.out.println("Erro ao sacar: " + e.getMessage());
        }
    }

    public void exibirMovimentacoes() {
        System.out.println("\n--- EXTRATO DE MOVIMENTAÇÕES ---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        var lista = contaService.obterMovimentacoes();
        if (lista.isEmpty()) {
            System.out.println("Não há transações registradas.");
            return;
        }

        for (Movimentacao m : lista) {
            System.out.printf("[%s] %-11s : %s%n",
                    m.getDataHora().format(formatter),
                    m.getTipo(),
                    m.getValor());
        }
    }
}