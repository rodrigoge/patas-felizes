package br.com.pf.api.domains.pets.services;

import br.com.pf.api.domains.account.db.AccountRepository;
import br.com.pf.api.domains.pets.db.PetRepository;
import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.CreatePetResponseDTO;
import br.com.pf.api.domains.pets.mappers.PetMapper;
import br.com.pf.api.exceptions.GenericException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AccountRepository accountRepository;

    public CreatePetResponseDTO createPet(UUID accountId, CreatePetRequestDTO createPetRequest) {
        log.info("Starting the create pet flow");
        var account = accountRepository.findById(accountId).orElseThrow(() -> new GenericException(
                HttpStatus.BAD_REQUEST, "This giver don't exists"
        ));
        var pet = petMapper.buildRequestToPet(createPetRequest, account);
        var petSaved = petRepository.save(pet);
        var petResponse = petMapper.buildPetToPetResponse(petSaved);
        log.info("Finishing the create pet flow");
        return petResponse;
    }
}
