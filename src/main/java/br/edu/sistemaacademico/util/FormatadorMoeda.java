package br.edu.sistemaacademico.util;

import java.util.Locale;

/** Formata valores em reais (ex.: 180.0 -> "R$ 180,00"), independentemente do idioma do sistema. */
public final class FormatadorMoeda {

    private static final Locale PT_BR = Locale.of("pt", "BR");

    private FormatadorMoeda() {
    }

    public static String formatar(double valor) {
        return String.format(PT_BR, "R$ %.2f", valor);
    }
}
