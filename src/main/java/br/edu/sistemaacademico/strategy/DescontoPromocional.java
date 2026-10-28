package br.edu.sistemaacademico.strategy;

/** Desconto promocional de 20%. */
public class DescontoPromocional implements Desconto {

    private static final double FATOR = 0.80;

    @Override
    public double aplicar(double valor) {
        return valor * FATOR;
    }
}
