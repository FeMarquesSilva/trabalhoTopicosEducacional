package br.grupointegrado.projetoTDE.controller;

import br.grupointegrado.projetoTDE.model.Turma;
import br.grupointegrado.projetoTDE.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        if (turma.isPresent()) {
            return ResponseEntity.ok(turma.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public Turma save(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody Turma turma) {
        Optional<Turma> turmaExistente = turmaRepository.findById(id);
        if (turmaExistente.isPresent()) {
            Turma turmaAtualizado = turmaExistente.get();
            turmaAtualizado.setAno(turma.getAno());
            turmaAtualizado.setSemestre(turma.getSemestre());
            turmaAtualizado.setCurso(turma.getCurso());
            return ResponseEntity.ok(turmaAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        if (turma.isPresent()) {
            turmaRepository.delete(turma.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
