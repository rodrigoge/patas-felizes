package br.com.pf.api.domains.pets.dto;

import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.pets.enums.PetType;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreatePetRequestDTO(
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

        UUID giverId,

        UUID receiverId
) {
}
