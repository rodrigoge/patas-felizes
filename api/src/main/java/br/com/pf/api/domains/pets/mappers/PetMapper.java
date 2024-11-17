package br.com.pf.api.domains.pets.mappers;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.account.mappers.AccountMapper;
import br.com.pf.api.domains.pets.db.Pet;
import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.PetResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@Log4j2
public class PetMapper {

    @Autowired
    private AccountMapper accountMapper;

    public Pet buildRequestToPet(CreatePetRequestDTO createPetRequest, Account giver, Account receiver) {
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
                .receiver(receiver)
                .build();
    }

    public PetResponseDTO buildPetToPetResponse(Pet pet) {
        log.info("Building the object to response");
        var giver = ObjectUtils.isEmpty(pet.getGiver()) ? null :
                accountMapper.buildAccountToAccountResponse(pet.getGiver());
        var receiver = ObjectUtils.isEmpty(pet.getReceiver()) ? null :
                accountMapper.buildAccountToAccountResponse(pet.getReceiver());
        return new PetResponseDTO(
                pet.getName(),
                pet.getAddress(),
                pet.getType(),
                pet.getAge(),
                pet.getBreed(),
                pet.getAvatar(),
                giver,
                receiver
        );
    }
}
