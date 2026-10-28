package br.edu.sistemaacademico.model;

/**
 * Curso oferecido pelo sistema. Objeto imutável: os atributos são
 * {@code private final} e só podem ser definidos pelo construtor, que valida os dados.
 */
public class Curso {

    private final String titulo;
    private final double preco;

    public Curso(String titulo, double preco) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do curso não pode ser nulo ou vazio.");
        }
        if (Double.isNaN(preco) || Double.isInfinite(preco) || preco <= 0) {
            throw new IllegalArgumentException("O preço do curso deve ser um número maior que zero.");
        }
        this.titulo = titulo.trim();
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }
}
