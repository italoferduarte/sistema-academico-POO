package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.sistemaacademico.strategy.Desconto;
import br.edu.sistemaacademico.strategy.DescontoEstudante;
import br.edu.sistemaacademico.strategy.DescontoPromocional;
import br.edu.sistemaacademico.strategy.SemDesconto;
import org.junit.jupiter.api.Test;

class DescontoTest {

    @Test
    void semDescontoMantemValor() {
        assertEquals(200.0, new SemDesconto().aplicar(200.0), 0.0001);
    }

    @Test
    void descontoEstudanteAplica10Porcento() {
        assertEquals(180.0, new DescontoEstudante().aplicar(200.0), 0.0001);
    }

    @Test
    void descontoPromocionalAplica20Porcento() {
        assertEquals(160.0, new DescontoPromocional().aplicar(200.0), 0.0001);
    }

    @Test
    void novaEstrategiaFuncionaSemAlterarOContrato() {
        Desconto metade = valor -> valor / 2; // exemplo de nova política
        assertEquals(100.0, metade.aplicar(200.0), 0.0001);
    }
}
