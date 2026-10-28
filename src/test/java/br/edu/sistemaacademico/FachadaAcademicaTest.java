package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.edu.sistemaacademico.adapter.AdaptadorPagamento;
import br.edu.sistemaacademico.adapter.SistemaLegado;
import br.edu.sistemaacademico.facade.FachadaAcademica;
import br.edu.sistemaacademico.factory.CriadorResumido;
import br.edu.sistemaacademico.factory.CriadorTexto;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.service.ServicoMatricula;
import br.edu.sistemaacademico.strategy.DescontoEstudante;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class FachadaAcademicaTest {

    private final List<Integer> centavos = new ArrayList<>();
    private final List<String> avisos = new ArrayList<>();

    private ServicoMatricula novoServico() {
        SistemaLegado legadoFalso = new SistemaLegado() {
            @Override
            public void registrarEmCentavos(int valor) {
                centavos.add(valor);
            }
        };
        ServicoMatricula servico = new ServicoMatricula(new DescontoEstudante(), new AdaptadorPagamento(legadoFalso));
        servico.inscrever(avisos::add);
        return servico;
    }

    @Test
    void fluxoCompletoComComprovanteTexto() {
        Aluno aluno = new Aluno("Ana");
        FachadaAcademica fachada = new FachadaAcademica(novoServico(), new CriadorTexto());
        String comprovante = fachada.matricularEEmitir(aluno, new Curso("Java", 200.0));
        assertTrue(comprovante.contains("Ana"));
        assertTrue(comprovante.contains("R$ 180,00"));
        assertEquals(List.of(18000), centavos);
        assertEquals(1, avisos.size());
        assertEquals(1, aluno.getQuantidadeCursos());
    }

    @Test
    void fluxoCompletoComComprovanteResumido() {
        Aluno aluno = new Aluno("Ana");
        FachadaAcademica fachada = new FachadaAcademica(novoServico(), new CriadorResumido());
        assertEquals("Ana | Java | R$ 180,00", fachada.matricularEEmitir(aluno, new Curso("Java", 200.0)));
    }

    @Test
    void rejeitaDependenciasNulas() {
        assertThrows(IllegalArgumentException.class, () -> new FachadaAcademica(null, new CriadorTexto()));
        assertThrows(IllegalArgumentException.class, () -> new FachadaAcademica(novoServico(), null));
    }
}
