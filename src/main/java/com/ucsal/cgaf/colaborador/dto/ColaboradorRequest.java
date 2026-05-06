package com.ucsal.cgaf.colaborador.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ColaboradorRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "E-mail deve ser válido")
        String email,

        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas os 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "Matrícula é obrigatória")
        String matricula,

        @NotBlank(message = "Função é obrigatória")
        String funcao,

        @NotBlank(message = "Área de atuação é obrigatória")
        String areaAtuacao,

        @NotNull(message = "Data de admissão é obrigatória")
        @PastOrPresent(message = "A data de admissão não pode ser uma data futura")
        LocalDate dataAdmissao,

        @NotBlank(message = "Contato de emergência é obrigatório")
        String contatoEmergencia,

        String qualificacoes
) {
}
