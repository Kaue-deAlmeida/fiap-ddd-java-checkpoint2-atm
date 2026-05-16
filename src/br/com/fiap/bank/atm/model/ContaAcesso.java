package br.com.fiap.bank.atm.model;

import java.util.Objects;

public final class ContaAcesso {
    public static final Integer MAXIMO_TENTATIVAS = 3;
    private final String senha;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = Boolean.FALSE;
    }

    public Boolean validarSenha(String senha) {
        if (bloqueado) return Boolean.FALSE;

        if (this.senha.equals(senha)) {
            resetarTentativas();
            return Boolean.TRUE;
        } else {
            tentativas++;
            if (tentativas >= MAXIMO_TENTATIVAS) {
                bloqueado = Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    public Boolean isBloqueado() { return bloqueado; }
    public void resetarTentativas() { this.tentativas = 0; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaAcesso that = (ContaAcesso) obj;
        return Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }
}