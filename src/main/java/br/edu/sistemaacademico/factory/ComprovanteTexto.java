package br.edu.sistemaacademico.factory;

import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.util.FormatadorMoeda;

/** Comprovante completo, em várias linhas. */
public class ComprovanteTexto implements Comprovante {

    @Override
    public String gerar(Aluno aluno, Curso curso, double valor) {
        return "=== COMPROVANTE DE MATRÍCULA ===\n"
                + "Aluno: " + aluno.getNome() + "\n"
                + "Curso: " + curso.getTitulo() + "\n"
                + "Valor pago: " + FormatadorMoeda.formatar(valor) + "\n"
                + "================================";
    }
}
