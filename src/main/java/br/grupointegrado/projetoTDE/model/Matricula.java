package br.grupointegrado.projetoTDE.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "aluno_id")
    private Integer alunoId;

    @Column(name = "turma_id")
    private Integer turmaId;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Integer alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Integer turmaId) {
        this.turmaId = turmaId;
    }
}
