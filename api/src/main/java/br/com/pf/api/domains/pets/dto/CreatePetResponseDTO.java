package br.com.pf.api.domains.pets.dto;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.pets.enums.PetType;
import jakarta.validation.constraints.NotBlank;

public record CreatePetResponseDTO(
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
        Account giver,

        Account receiver

) {
}
