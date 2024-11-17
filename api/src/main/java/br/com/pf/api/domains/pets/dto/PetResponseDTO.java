package br.com.pf.api.domains.pets.dto;

import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.pets.enums.PetType;
import jakarta.validation.constraints.NotBlank;

public record PetResponseDTO(
        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotBlank
        PetType type,

        @NotBlank
        Integer age,

        @NotBlank
        String breed,

        byte[] avatar,

        @NotBlank
        AccountResponseDTO giver,

        AccountResponseDTO receiver

) {
}
