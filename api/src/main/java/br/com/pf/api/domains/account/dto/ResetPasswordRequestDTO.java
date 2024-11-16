package br.com.pf.api.domains.account.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
