package br.grupointegrado.projetoTDE.controller;

import br.grupointegrado.projetoTDE.model.Curso;
import br.grupointegrado.projetoTDE.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

        @PostMapping
        public ResponseEntity<Curso> save(@RequestBody String nome, String codigo, String telefone, Integer carga_horaria) {
            Curso curso = new Curso();
            curso.setNome(nome);
            curso.setCodigo(codigo);
            curso.setTelefone(telefone);
            curso.setCarga_horaria(carga_horaria);
            this.repository.save(curso);

            return ResponseEntity.ok(curso);
        }
}