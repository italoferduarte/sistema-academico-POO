package br.edu.sistemaacademico.observer;

/** Padrão OBSERVER: quem quiser ser avisado de uma matrícula implementa esta interface. */
public interface ObservadorMatricula {

    void atualizar(String mensagem);
}
