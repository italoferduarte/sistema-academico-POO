package br.edu.sistemaacademico.facade;

import br.edu.sistemaacademico.factory.CriadorComprovante;
import br.edu.sistemaacademico.model.Aluno;
import br.edu.sistemaacademico.model.Curso;
import br.edu.sistemaacademico.service.ServicoMatricula;

/** Padrão FACADE: uma única chamada simples esconde matrícula + emissão do comprovante. */
public class FachadaAcademica {

    private final ServicoMatricula servico;
    private final CriadorComprovante criador;

    public FachadaAcademica(ServicoMatricula servico, CriadorComprovante criador) {
        if (servico == null || criador == null) {
            throw new IllegalArgumentException("Serviço de matrícula e criador de comprovante são obrigatórios.");
        }
        this.servico = servico;
        this.criador = criador;
    }

    public String matricularEEmitir(Aluno aluno, Curso curso) {
        double valorFinal = servico.matricular(aluno, curso);
        return criador.emitir(aluno, curso, valorFinal);
    }
}
