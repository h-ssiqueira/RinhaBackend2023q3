package com.hss.rinhabackend.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String apelido;
    private String nome;
    private LocalDate nascimento;
    private List<String> stack;

    public UUID getId() {
        return id;
    }

    public Pessoa id(UUID id) {
        this.id = id;
        return this;
    }

    public String getApelido() {
        return apelido;
    }

    public Pessoa apelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Pessoa nascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public List<String> getStack() {
        return stack;
    }

    public Pessoa stack(List<String> stack) {
        this.stack = stack;
        return this;
    }
}
