package br.com.pf.api.domains.pets.dto;

import br.com.pf.api.domains.pets.enums.PetType;

import java.util.UUID;

public record GetPetsRequestDTO(
        UUID petId,
        String name,
        PetType type,
        String breed,
        UUID giverId
) {
}
