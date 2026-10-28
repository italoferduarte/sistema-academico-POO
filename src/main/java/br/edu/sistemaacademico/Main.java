package br.edu.sistemaacademico;

import br.edu.sistemaacademico.adapter.AdaptadorPagamento;
import br.edu.sistemaacademico.adapter.SistemaLegado;
import br.edu.sistemaacademico.config.Configuracao;
import br.edu.sistemaacademico.facade.FachadaAcademica;
import br.edu.sistemaacademico.factory.CriadorComprovante;
import br.edu.sistemaacademico.factory.CriadorTexto;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.observer.Auditoria;
import br.edu.sistemaacademico.observer.AvisoEmail;
import br.edu.sistemaacademico.service.ServicoMatricula;
import br.edu.sistemaacademico.strategy.Desconto;
import br.edu.sistemaacademico.strategy.DescontoEstudante;

/** Monta as peças e executa o fluxo de exemplo. A lógica de negócio fica nas outras classes. */
public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema: " + Configuracao.getInstancia().getNome());

        Aluno aluno = new Aluno("Ana");
        Curso curso = new Curso("Java", 200.00);

        Desconto desconto = new DescontoEstudante();
        AdaptadorPagamento pagamento = new AdaptadorPagamento(new SistemaLegado());

        ServicoMatricula servico = new ServicoMatricula(desconto, pagamento);
        servico.inscrever(new AvisoEmail());
        servico.inscrever(new Auditoria());

        CriadorComprovante criador = new CriadorTexto();
        FachadaAcademica fachada = new FachadaAcademica(servico, criador);

        String comprovante = fachada.matricularEEmitir(aluno, curso);

        System.out.println(comprovante);
        System.out.println("Cursos de " + aluno.getNome() + ": " + aluno.getQuantidadeCursos());
    }
}
