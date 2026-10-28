package br.edu.sistemaacademico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import br.edu.sistemaacademico.config.Configuracao;
import org.junit.jupiter.api.Test;

class ConfiguracaoTest {

    @Test
    void devolveSempreAMesmaInstancia() {
        assertSame(Configuracao.getInstancia(), Configuracao.getInstancia());
    }

    @Test
    void temNomeInicialEsperado() {
        assertEquals("Sistema Acadêmico", Configuracao.getInstancia().getNome());
    }
}
