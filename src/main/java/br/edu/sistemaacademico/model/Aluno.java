package br.edu.sistemaacademico.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aluno do sistema. Mantém uma lista de {@link Curso}: relação de ASSOCIAÇÃO
 * (o aluno "tem" cursos, mas os cursos existem independentemente dele).
 */
public class Aluno {

    private final String nome;
    private final List<Curso> cursos = new ArrayList<>();

    public Aluno(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do aluno não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public void adicionarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("O curso não pode ser nulo.");
        }
        cursos.add(curso);
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeCursos() {
        return cursos.size();
    }

    /** Visão somente leitura, para não permitir alterar a lista por fora. */
    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }
}
