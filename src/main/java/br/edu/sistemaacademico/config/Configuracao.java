package br.edu.sistemaacademico.config;

/**
 * Padrão SINGLETON: existe uma única instância, criada na carga da classe
 * (inicialização antecipada, segura para threads por garantia da JVM).
 */
public final class Configuracao {

    private static final Configuracao INSTANCIA = new Configuracao();

    private final String nome = "Sistema Acadêmico";

    private Configuracao() {
        // construtor privado: ninguém de fora consegue fazer "new Configuracao()"
    }

    public static Configuracao getInstancia() {
        return INSTANCIA;
    }

    public String getNome() {
        return nome;
    }
}
