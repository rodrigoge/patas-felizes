package br.com.pf.api.domains.pets.services;

import br.com.pf.api.domains.pets.db.PetRepository;
import br.com.pf.api.domains.pets.dto.CreatePetDTO;
import br.com.pf.api.domains.pets.mappers.PetMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetRepository petRepository;

    public CreatePetDTO createPet(CreatePetDTO createPetRequest) {
        log.info("Starting the create pet flow");
        var pet = petMapper.buildRequestToPet(createPetRequest);
        var petSaved = petRepository.save(pet);
        var petResponse = petMapper.buildPetToPetResponse(petSaved);
        log.info("Finishing the create pet flow");
        return petResponse;
    }
}
