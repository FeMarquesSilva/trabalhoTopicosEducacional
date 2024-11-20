package br.grupointegrado.projetoTDE.controller;

import br.grupointegrado.projetoTDE.model.Curso;
import br.grupointegrado.projetoTDE.model.Disciplina;
import br.grupointegrado.projetoTDE.model.Professor;
import br.grupointegrado.projetoTDE.repository.DisciplinaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    //Listar Disciplinas;
    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        return ResponseEntity.ok(this.disciplinaRepository.findAll());
    }

    //Função para buscar uma disciplina pelo id;
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isPresent()) {
            return ResponseEntity.ok(disciplina.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Cadastrar nova disciplina;
    @PostMapping
    public Disciplina save(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    //Função para atualizar uma disciplina pelo id;
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody Disciplina disciplinaDetails) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            disciplina.setNome(disciplinaDetails.getNome());
            disciplina.setCodigo(disciplinaDetails.getCodigo());
            disciplina.setCurso(disciplinaDetails.getCurso());
            disciplina.setProfessor(disciplinaDetails.getProfessor());

            disciplinaRepository.save(disciplina);
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Função para deletar um curso pelo id;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            disciplinaRepository.delete(optionalDisciplina.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
