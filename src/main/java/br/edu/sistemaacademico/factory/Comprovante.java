package br.edu.sistemaacademico.factory;

import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;

/** Produto do Factory Method. */
public interface Comprovante {

    String gerar(Aluno aluno, Curso curso, double valor);
}
