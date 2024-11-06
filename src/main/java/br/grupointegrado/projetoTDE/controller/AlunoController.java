package br.grupointegrado.projetoTDE.controller;


import br.grupointegrado.projetoTDE.model.Aluno;
import br.grupointegrado.projetoTDE.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/alunos")
public class AlunoController {


    @Autowired
    private AlunoRepository alunoRepository;

    // Método para buscar todos os alunos
    @GetMapping
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    // Método para buscar um aluno pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para salvar um novo aluno
    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Método para atualizar um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            Aluno alunoAtualizado = alunoExistente.get();
            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setEmail(aluno.getEmail());
            alunoAtualizado.setMatricula(aluno.getMatricula());
            alunoAtualizado.setDataNascimento(aluno.getDataNascimento());
            alunoRepository.save(alunoAtualizado);
            return ResponseEntity.ok(alunoAtualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para deletar um aluno pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            alunoRepository.delete(aluno.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
