package com.projetoInitial.apiInitial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String nome,

        String telefone,

        @Email(message = "E necessario um email valido!")
        String email
) {

}