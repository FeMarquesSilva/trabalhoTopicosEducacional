package br.grupointegrado.projetoTDE.repository;

import br.grupointegrado.projetoTDE.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    // Busca notas por aluno
    List<Nota> findByAlunoId(Integer alunoId);

    // Busca notas por disciplina
    List<Nota> findByDisciplinaId(Integer disciplinaId);

    // Consulta customizada para médias de notas por turma
    @Query("SELECT d.nome AS disciplina, AVG(n.nota) AS media " +
            "FROM Nota n " +
            "JOIN n.disciplina d " +
            "JOIN n.matricula m " +
            "JOIN m.turma t " +
            "WHERE t.id = :turmaId " +
            "GROUP BY d.nome")
    List<Object[]> findMediaNotasPorTurma(Integer turmaId);

}
