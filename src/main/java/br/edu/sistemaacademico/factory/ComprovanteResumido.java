package br.edu.sistemaacademico.factory;

import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.util.FormatadorMoeda;

/** Comprovante compacto, em uma linha. */
public class ComprovanteResumido implements Comprovante {

    @Override
    public String gerar(Aluno aluno, Curso curso, double valor) {
        return aluno.getNome() + " | " + curso.getTitulo() + " | " + FormatadorMoeda.formatar(valor);
    }
}
