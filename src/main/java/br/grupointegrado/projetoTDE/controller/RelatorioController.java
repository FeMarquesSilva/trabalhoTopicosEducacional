package br.grupointegrado.projetoTDE.controller;

import br.grupointegrado.projetoTDE.model.Nota;
import br.grupointegrado.projetoTDE.repository.NotaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final NotaRepository notaRepository;

    public RelatorioController(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    /**
     * Retorna o desempenho (média de notas) por disciplina de uma turma.
     * @param turmaId ID da turma.
     * @return Lista de mapas contendo o nome da disciplina e a média das notas.
     */
    @GetMapping("/relatorios/desempenho-por-turma")
    public List<Map<String, Object>> getDesempenhoPorTurma(@RequestParam Integer turmaId) {
        // Obtém o resultado da consulta customizada
        List<Object[]> desempenho = notaRepository.findMediaNotasPorTurma(turmaId);

        // Converte o resultado para uma lista de mapas
        return desempenho.stream().map(obj -> {
            Map<String, Object> item = new HashMap<>();
            item.put("disciplina", obj[0]); // Nome da disciplina
            item.put("media", obj[1]);     // Média das notas
            return item;
        }).toList();
    }

    /**
     * Retorna todas as notas de um aluno específico, organizadas por disciplina.
     * @param alunoId ID do aluno.
     * @return Lista de notas do aluno organizadas por disciplina.
     */
    @GetMapping("/relatorios/notas-por-aluno")
    public List<Nota> getNotasPorAluno(@RequestParam Integer alunoId) {
        return notaRepository.findByAlunoId(alunoId);
    }

    /**
     * Retorna o desempenho de todos os alunos em uma disciplina específica.
     * @param disciplinaId ID da disciplina.
     * @return Lista de mapas contendo o nome do aluno e a nota obtida.
     */
    @GetMapping("/relatorios/desempenho-por-disciplina")
    public List<Map<String, Object>> getDesempenhoPorDisciplina(@RequestParam Integer disciplinaId) {
        // Obtém as notas da disciplina
        List<Nota> notas = notaRepository.findByDisciplinaId(disciplinaId);

        // Converte para um formato legível, incluindo detalhes do aluno e nota
        return notas.stream().map(nota -> {
            Map<String, Object> item = new HashMap<>();
            item.put("alunoId", nota.getMatricula().getAlunoId());
            item.put("nomeAluno", nota.getMatricula().getAlunoId());
            item.put("nota", nota.getNota());
            return item;
        }).toList();
    }
}
