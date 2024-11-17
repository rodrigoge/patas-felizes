package br.com.pf.api.domains.pets.mappers;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.pets.db.Pet;
import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.CreatePetResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PetMapper {

    public Pet buildRequestToPet(CreatePetRequestDTO createPetRequest, Account giver) {
        log.info("Building the object to be save");
        return Pet
                .builder()
                .name(createPetRequest.name())
                .address(createPetRequest.address())
                .type(createPetRequest.type())
                .age(createPetRequest.age())
                .breed(createPetRequest.breed())
                .avatar(createPetRequest.avatar())
                .giver(giver)
                .build();
    }

    public CreatePetResponseDTO buildPetToPetResponse(Pet pet) {
        log.info("Building the object to response");
        return new CreatePetResponseDTO(
                pet.getName(),
                pet.getAddress(),
                pet.getType(),
                pet.getAge(),
                pet.getBreed(),
                pet.getAvatar(),
                pet.getGiver(),
                pet.getReceiver()
        );
    }
}
