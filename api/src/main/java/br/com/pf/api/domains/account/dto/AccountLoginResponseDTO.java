package br.com.pf.api.domains.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountLoginResponseDTO(
        @NotBlank
        AccountResponseDTO accountResponse,

        @NotBlank
        String token
) {
}
