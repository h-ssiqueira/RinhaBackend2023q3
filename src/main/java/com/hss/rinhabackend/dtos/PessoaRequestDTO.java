package com.hss.rinhabackend.dtos;

import com.hss.rinhabackend.config.NotEmptyList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequestDTO(@NotBlank(message = "apelido é obrigatório") @Size(max=32, min=1, message = "apelido deve ter até 32 caracteres") String apelido,
                               @NotBlank(message = "nome é obrigatório") @Size(max=100, min=1, message = "apelido deve ter até 100 caracteres") String nome,
                               @NotNull(message = "nascimento é obrigatório") LocalDate nascimento,
                               @NotEmptyList(message = "stack não pode estar vazia caso informada") List<String> stack) {

}
