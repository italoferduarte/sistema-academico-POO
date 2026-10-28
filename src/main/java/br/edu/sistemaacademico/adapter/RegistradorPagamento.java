package br.edu.sistemaacademico.adapter;

/** Contrato que a aplicação conhece: trabalha em reais. */
public interface RegistradorPagamento {

    void registrar(double reais);
}
