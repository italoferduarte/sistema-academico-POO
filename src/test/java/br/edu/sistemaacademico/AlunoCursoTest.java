package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import org.junit.jupiter.api.Test;

class AlunoCursoTest {

    @Test
    void criaAlunoECursoValidos() {
        Aluno aluno = new Aluno("Ana");
        Curso curso = new Curso("Java", 200.0);
        assertEquals("Ana", aluno.getNome());
        assertEquals(0, aluno.getQuantidadeCursos());
        assertEquals("Java", curso.getTitulo());
        assertEquals(200.0, curso.getPreco(), 0.0001);
    }

    @Test
    void rejeitaNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Aluno(null));
        assertThrows(IllegalArgumentException.class, () -> new Aluno(""));
        assertThrows(IllegalArgumentException.class, () -> new Aluno("   "));
    }

    @Test
    void rejeitaCursoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Curso(null, 100));
        assertThrows(IllegalArgumentException.class, () -> new Curso(" ", 100));
        assertThrows(IllegalArgumentException.class, () -> new Curso("Java", 0));
        assertThrows(IllegalArgumentException.class, () -> new Curso("Java", -5));
        assertThrows(IllegalArgumentException.class, () -> new Curso("Java", Double.NaN));
    }

    @Test
    void adicionaCursoAoAluno() {
        Aluno aluno = new Aluno("Ana");
        aluno.adicionarCurso(new Curso("Java", 200));
        assertEquals(1, aluno.getQuantidadeCursos());
    }

    @Test
    void rejeitaCursoNuloNoAluno() {
        Aluno aluno = new Aluno("Ana");
        assertThrows(IllegalArgumentException.class, () -> aluno.adicionarCurso(null));
        assertEquals(0, aluno.getQuantidadeCursos());
    }

    @Test
    void listaDeCursosExpostaNaoPodeSerAlteradaPorFora() {
        Aluno aluno = new Aluno("Ana");
        assertThrows(UnsupportedOperationException.class,
                () -> aluno.getCursos().add(new Curso("Java", 200)));
    }
}
