package com.projetoInitial.apiInitial.dto;

import com.projetoInitial.apiInitial.models.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AnimalDTO(

         String especie,

         @NotNull(message = "A idade é obrigatório!")
         @Positive(message = "A idade deve ser maior que zero!")
         int idade,

         @NotBlank(message = "O nome é obrigatório!")
         String nome,

         String raca,

         Tutor tutor
) {

}