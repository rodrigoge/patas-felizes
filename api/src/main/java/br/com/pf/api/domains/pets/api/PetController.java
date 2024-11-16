package br.com.pf.api.domains.pets.api;

import br.com.pf.api.domains.account.dto.AccountLoginRequestDTO;
import br.com.pf.api.domains.account.dto.AccountLoginResponseDTO;
import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.account.dto.ResetPasswordRequestDTO;
import br.com.pf.api.domains.account.services.AccountService;
import br.com.pf.api.domains.account.services.EmailService;
import br.com.pf.api.domains.pets.dto.CreatePetDTO;
import br.com.pf.api.domains.pets.services.PetService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pets")
@Log4j2
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<CreatePetDTO> createPet(@Valid @RequestBody CreatePetDTO createPetRequest) {
        log.info("Entering the create pet flow");
        var accountResponse = petService.createPet(createPetRequest);
        log.info("Exiting the create pet flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
