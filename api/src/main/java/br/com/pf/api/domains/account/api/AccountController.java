package br.com.pf.api.domains.account.api;

import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.account.services.AccountService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/accounts")
@Log4j2
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO accountRequest) {
        log.info("Entering the create account flow");
        var accountResponse = accountService.createAccount(accountRequest);
        log.info("Exiting the create account flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable UUID accountId,
                                                            @Valid @RequestBody AccountRequestDTO accountRequest) {
        log.info("Entering the update account flow");
        var accountResponse = accountService.updateAccount(accountId, accountRequest);
        log.info("Exiting the update account flow");
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
