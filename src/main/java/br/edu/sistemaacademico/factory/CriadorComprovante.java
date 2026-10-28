package br.edu.sistemaacademico.factory;

import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;

/**
 * Padrão FACTORY METHOD: {@link #criar()} é o método-fábrica, implementado pelas
 * subclasses. {@link #emitir(Aluno, Curso, double)} usa o produto sem saber qual é (polimorfismo).
 */
public abstract class CriadorComprovante {

    protected abstract Comprovante criar();

    public String emitir(Aluno aluno, Curso curso, double valor) {
        if (aluno == null || curso == null) {
            throw new IllegalArgumentException("Aluno e curso são obrigatórios para emitir o comprovante.");
        }
        return criar().gerar(aluno, curso, valor);
    }
}
