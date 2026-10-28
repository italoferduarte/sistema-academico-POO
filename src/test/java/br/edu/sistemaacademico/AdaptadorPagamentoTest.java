package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.edu.sistemaacademico.adapter.AdaptadorPagamento;
import br.edu.sistemaacademico.adapter.SistemaLegado;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AdaptadorPagamentoTest {

    /** Dublê do sistema legado: guarda o que recebeu em vez de imprimir. */
    private static class LegadoFalso extends SistemaLegado {
        final List<Integer> recebidos = new ArrayList<>();

        @Override
        public void registrarEmCentavos(int centavos) {
            recebidos.add(centavos);
        }
    }

    @Test
    void converteReaisParaCentavos() {
        LegadoFalso legado = new LegadoFalso();
        new AdaptadorPagamento(legado).registrar(180.0);
        assertEquals(List.of(18000), legado.recebidos);
    }

    @Test
    void arredondaCorretamenteValorComErroDeDouble() {
        LegadoFalso legado = new LegadoFalso();
        new AdaptadorPagamento(legado).registrar(19.99); // 19.99 * 100 = 1998.9999999999998
        assertEquals(List.of(1999), legado.recebidos);
    }

    @Test
    void rejeitaValoresInvalidosSemChamarOLegado() {
        LegadoFalso legado = new LegadoFalso();
        AdaptadorPagamento adaptador = new AdaptadorPagamento(legado);
        assertThrows(IllegalArgumentException.class, () -> adaptador.registrar(0));
        assertThrows(IllegalArgumentException.class, () -> adaptador.registrar(-1));
        assertThrows(IllegalArgumentException.class, () -> adaptador.registrar(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> adaptador.registrar(1e12));
        assertTrue(legado.recebidos.isEmpty());
    }

    @Test
    void rejeitaLegadoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new AdaptadorPagamento(null));
    }
}
