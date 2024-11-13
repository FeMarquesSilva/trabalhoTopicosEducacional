package br.grupointegrado.projetoTDE.controller;

import br.grupointegrado.projetoTDE.model.Aluno;
import br.grupointegrado.projetoTDE.model.Professor;
import br.grupointegrado.projetoTDE.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            return ResponseEntity.ok(professor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public Professor save(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

}
