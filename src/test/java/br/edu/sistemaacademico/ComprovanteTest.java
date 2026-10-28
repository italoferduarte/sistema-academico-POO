package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.edu.sistemaacademico.factory.CriadorComprovante;
import br.edu.sistemaacademico.factory.CriadorResumido;
import br.edu.sistemaacademico.factory.CriadorTexto;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import org.junit.jupiter.api.Test;

class ComprovanteTest {

    private final Aluno aluno = new Aluno("Ana");
    private final Curso curso = new Curso("Java", 200.0);

    @Test
    void comprovanteTextoTrazAlunoCursoEValor() {
        CriadorComprovante criador = new CriadorTexto();
        String texto = criador.emitir(aluno, curso, 180.0);
        assertTrue(texto.contains("Aluno: Ana"));
        assertTrue(texto.contains("Curso: Java"));
        assertTrue(texto.contains("R$ 180,00"));
    }

    @Test
    void comprovanteResumidoEhCompactoEIdentificaAlunoEValor() {
        CriadorComprovante criador = new CriadorResumido();
        assertEquals("Ana | Java | R$ 180,00", criador.emitir(aluno, curso, 180.0));
    }

    @Test
    void emitirRejeitaDadosNulos() {
        CriadorComprovante criador = new CriadorTexto();
        assertThrows(IllegalArgumentException.class, () -> criador.emitir(null, curso, 10));
        assertThrows(IllegalArgumentException.class, () -> criador.emitir(aluno, null, 10));
    }
}
