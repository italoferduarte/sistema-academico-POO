package br.edu.sistemaacademico.observer;

/** Simula o envio de e-mail escrevendo no terminal (nenhum e-mail real é enviado). */
public class AvisoEmail implements ObservadorMatricula {

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[E-mail simulado] " + mensagem);
    }
}
