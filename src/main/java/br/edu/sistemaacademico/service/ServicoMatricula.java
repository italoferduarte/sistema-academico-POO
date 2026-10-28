package br.edu.sistemaacademico.service;

import br.edu.sistemaacademico.adapter.RegistradorPagamento;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.observer.ObservadorMatricula;
import br.edu.sistemaacademico.strategy.Desconto;
import br.edu.sistemaacademico.util.FormatadorMoeda;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordena a matrícula: desconto (Strategy) -> pagamento (Adapter via contrato) ->
 * associação aluno/curso -> notificação (Observer). Só conhece interfaces.
 * Se qualquer passo antes da associação falhar, nada é associado nem notificado.
 */
public class ServicoMatricula {

    private final Desconto desconto;
    private final RegistradorPagamento registrador;
    private final List<ObservadorMatricula> observadores = new ArrayList<>();

    public ServicoMatricula(Desconto desconto, RegistradorPagamento registrador) {
        if (desconto == null || registrador == null) {
            throw new IllegalArgumentException("Desconto e registrador de pagamento são obrigatórios.");
        }
        this.desconto = desconto;
        this.registrador = registrador;
    }

    public void inscrever(ObservadorMatricula observador) {
        if (observador == null) {
            throw new IllegalArgumentException("O observador não pode ser nulo.");
        }
        observadores.add(observador);
    }

    /** @return valor final cobrado, já com desconto */
    public double matricular(Aluno aluno, Curso curso) {
        if (aluno == null || curso == null) {
            throw new IllegalArgumentException("Aluno e curso são obrigatórios para a matrícula.");
        }
        double valorFinal = desconto.aplicar(curso.getPreco());
        registrador.registrar(valorFinal);
        aluno.adicionarCurso(curso);
        notificar("Matrícula realizada: aluno " + aluno.getNome() + " no curso "
                + curso.getTitulo() + " (valor final " + FormatadorMoeda.formatar(valorFinal) + ")");
        return valorFinal;
    }

    private void notificar(String mensagem) {
        for (ObservadorMatricula observador : observadores) {
            observador.atualizar(mensagem);
        }
    }
}
