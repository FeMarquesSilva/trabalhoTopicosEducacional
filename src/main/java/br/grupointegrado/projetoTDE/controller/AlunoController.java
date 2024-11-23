package br.grupointegrado.projetoTDE.controller;


import br.grupointegrado.projetoTDE.model.Aluno;
import br.grupointegrado.projetoTDE.model.Matricula;
import br.grupointegrado.projetoTDE.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/alunos")
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

    // Método para buscar notas de um aluno pelo ID
    @GetMapping("/{id}/relatorio")
    public ResponseEntity<Map<String, Object>> findNotasById(@PathVariable Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isPresent()) {
            // Cria um mapa para armazenar o nome do aluno e os IDs das matrículas
            Map<String, Object> response = new HashMap<>();

            // Adiciona o nome do aluno ao mapa
            response.put("nome", aluno.get().getNome());

            // Inicializa a lista para armazenar os IDs das matrículas
            List<Integer> matriculasIds = new ArrayList<>();

            // Preenche a lista com os IDs das matrículas
            for (Matricula matricula : aluno.get().getMatriculas()) {
                matriculasIds.add(matricula.getId());
            }

            // Adiciona a lista de IDs das matrículas ao mapa
            response.put("matriculas", matriculasIds);

            // Retorna o nome do aluno e os IDs das matrículas
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
