package br.edu.sistemaacademico.strategy;

/** Padrão STRATEGY: contrato comum para as políticas de desconto. */
public interface Desconto {

    double aplicar(double valor);
}
