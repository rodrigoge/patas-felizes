package br.com.pf.api.domains.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String password,

        byte[] avatar
) {
}
