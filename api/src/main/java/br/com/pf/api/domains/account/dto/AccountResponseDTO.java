package br.com.pf.api.domains.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountResponseDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        byte[] avatar) {
}
