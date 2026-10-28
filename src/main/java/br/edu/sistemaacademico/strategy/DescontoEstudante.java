package br.edu.sistemaacademico.strategy;

/** Desconto de 10% para estudantes. */
public class DescontoEstudante implements Desconto {

    private static final double FATOR = 0.90;

    @Override
    public double aplicar(double valor) {
        return valor * FATOR;
    }
}
