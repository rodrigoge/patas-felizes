package br.com.pf.api.domains.pets.api;

import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.CreatePetResponseDTO;
import br.com.pf.api.domains.pets.services.PetService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pets")
@Log4j2
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/{accountId}")
    public ResponseEntity<CreatePetResponseDTO> createPet(@PathVariable UUID accountId, @Valid @RequestBody CreatePetRequestDTO createPetRequest) {
        log.info("Entering the create pet flow");
        var accountResponse = petService.createPet(accountId, createPetRequest);
        log.info("Exiting the create pet flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
