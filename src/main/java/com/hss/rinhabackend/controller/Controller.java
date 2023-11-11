package com.hss.rinhabackend.controller;

import com.hss.rinhabackend.dtos.PessoaRequestDTO;
import com.hss.rinhabackend.repository.Pessoa;
import com.hss.rinhabackend.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class Controller {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    private final PessoaRepository repository;

    public Controller(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/pessoas")
    public ResponseEntity<List<Pessoa>> getPessoas(@RequestParam(name = "t") String t) {
        LOG.info(String.format("Buscando pessoa por parametro t: %s", t));
        return ResponseEntity.ok(repository.findByTermo(t));
    }

    @GetMapping(path = "/contagem-pessoas")
    public ResponseEntity<Long> getContagemPessoas() {
        LOG.info("Contando total de pessoas.");
        return ResponseEntity.ok(repository.countPessoas());
    }

    @GetMapping(path = "/pessoas/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable("id") String id) {
        LOG.info(String.format("Buscando pessoa por id: %s", id));
        return repository.findById(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/pessoas")
    public ResponseEntity<String> createPessoa(@RequestBody @Valid PessoaRequestDTO request) {
        LOG.info(String.format("Criando pessoa: %s", request.toString()));
        if(repository.pessoaExiste(request.apelido())){
            LOG.info("Pessoa já existente.");
            return ResponseEntity.unprocessableEntity().build();
        }
        var response = repository.save(new Pessoa()
                .apelido(request.apelido())
                .nome(request.nome())
                .nascimento(request.nascimento())
                .stack(request.stack()));
        return ResponseEntity.created(URI.create(String.format("/pessoas/%s", response.getId().toString()))).build();
    }
}
