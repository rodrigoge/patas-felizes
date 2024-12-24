package br.com.pf.api.domains.pets.api;

import br.com.pf.api.domains.pets.db.Pet;
import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.GetPetsRequestDTO;
import br.com.pf.api.domains.pets.enums.PetType;
import br.com.pf.api.domains.pets.services.PetService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pets")
@Log4j2
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/{petId}")
    public ResponseEntity<Pet> createPet(@PathVariable UUID petId, @Valid @RequestBody CreatePetRequestDTO createPetRequest) {
        log.info("Entering the create pet flow");
        var petResponse = petService.createPet(petId, createPetRequest);
        log.info("Exiting the create pet flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponse);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(@RequestParam(value = "petId", required = false) UUID petId,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "type", required = false) PetType type,
                                             @RequestParam(value = "breed", required = false) String breed,
                                             @RequestParam(value = "giverId", required = false) UUID giverId
    ) {
        log.info("Entering the get pets flow");
        var getPetsRequest = new GetPetsRequestDTO(petId, name, type, breed, giverId);
        var petResponse = petService.findPets(getPetsRequest);
        log.info("Exiting the get pets flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(petResponse);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable UUID petId,
                                                    @Valid @RequestBody CreatePetRequestDTO petRequest) {
        log.info("Entering the update pet flow");
        var petResponse = petService.updatePet(petId, petRequest);
        log.info("Exiting the update pet flow");
        return ResponseEntity.status(HttpStatus.OK).body(petResponse);
    }
}
