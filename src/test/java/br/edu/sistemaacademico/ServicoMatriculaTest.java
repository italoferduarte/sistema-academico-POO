package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.edu.sistemaacademico.adapter.RegistradorPagamento;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.service.ServicoMatricula;
import br.edu.sistemaacademico.strategy.DescontoEstudante;
import br.edu.sistemaacademico.strategy.SemDesconto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ServicoMatriculaTest {

    private final Aluno aluno = new Aluno("Ana");
    private final Curso curso = new Curso("Java", 200.0);
    private final List<Double> pagamentos = new ArrayList<>();
    private final List<String> mensagens = new ArrayList<>();
    private final RegistradorPagamento registradorFalso = pagamentos::add;

    @Test
    void matriculaAplicaDescontoRegistraPagamentoEAssociaCurso() {
        ServicoMatricula servico = new ServicoMatricula(new DescontoEstudante(), registradorFalso);
        double valor = servico.matricular(aluno, curso);
        assertEquals(180.0, valor, 0.0001);
        assertEquals(1, pagamentos.size());
        assertEquals(180.0, pagamentos.get(0), 0.0001);
        assertEquals(1, aluno.getQuantidadeCursos());
    }

    @Test
    void notificaTodosOsObservadoresAposMatriculaBemSucedida() {
        ServicoMatricula servico = new ServicoMatricula(new SemDesconto(), registradorFalso);
        servico.inscrever(mensagens::add);
        servico.inscrever(mensagens::add);
        servico.matricular(aluno, curso);
        assertEquals(2, mensagens.size());
        assertTrue(mensagens.get(0).contains("Ana"));
        assertTrue(mensagens.get(0).contains("Java"));
    }

    @Test
    void falhaNoPagamentoNaoAssociaCursoNemNotifica() {
        RegistradorPagamento quebrado = reais -> {
            throw new IllegalStateException("falha simulada");
        };
        ServicoMatricula servico = new ServicoMatricula(new SemDesconto(), quebrado);
        servico.inscrever(mensagens::add);
        assertThrows(IllegalStateException.class, () -> servico.matricular(aluno, curso));
        assertEquals(0, aluno.getQuantidadeCursos());
        assertTrue(mensagens.isEmpty());
    }

    @Test
    void entradasInvalidasSaoRejeitadasSemEfeitoColateral() {
        ServicoMatricula servico = new ServicoMatricula(new SemDesconto(), registradorFalso);
        servico.inscrever(mensagens::add);
        assertThrows(IllegalArgumentException.class, () -> servico.matricular(null, curso));
        assertThrows(IllegalArgumentException.class, () -> servico.matricular(aluno, null));
        assertThrows(IllegalArgumentException.class, () -> servico.inscrever(null));
        assertThrows(IllegalArgumentException.class, () -> new ServicoMatricula(null, registradorFalso));
        assertThrows(IllegalArgumentException.class, () -> new ServicoMatricula(new SemDesconto(), null));
        assertTrue(pagamentos.isEmpty());
        assertTrue(mensagens.isEmpty());
    }
}
