package br.edu.sistemaacademico.observer;

/** Registra a ocorrência da matrícula no terminal. */
public class Auditoria implements ObservadorMatricula {

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[Auditoria] Registro: " + mensagem);
    }
}
