package br.com.pf.api.domains.account.api;

import br.com.pf.api.domains.account.dto.AccountLoginRequestDTO;
import br.com.pf.api.domains.account.dto.AccountLoginResponseDTO;
import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.account.dto.ResetPasswordRequestDTO;
import br.com.pf.api.domains.account.services.AccountService;
import br.com.pf.api.domains.account.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/accounts")
@Log4j2
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }

    @PostMapping("/send/email")
    public ResponseEntity<String> sendEmail(@Valid @RequestParam String email) throws MessagingException {
        log.info("Entering the send email flow");
        emailService.sendEmailToRecoveryPassword(email);
        log.info("Exiting the send email flow");
        return ResponseEntity.status(HttpStatus.OK).body("Email sent. Check your inbox or spam.");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<AccountResponseDTO> updatePassword(@RequestParam(value = "token") String token,
                                                             @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequest) {
        log.info("Entering the update password flow");
        var accountResponse = accountService.updatePassword(token, resetPasswordRequest);
        log.info("Exiting the update password flow");
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponseDTO> login(@Valid @RequestBody AccountLoginRequestDTO accountLoginRequest) {
        log.info("Entering the login flow");
        var authToken = new UsernamePasswordAuthenticationToken(
                accountLoginRequest.email(),
                accountLoginRequest.password()
        );
        var authenticate = authenticationManager.authenticate(authToken);
        var accountLoginResponse = accountService.login(authenticate);
        log.info("Exiting the login flow");
        return ResponseEntity.status(HttpStatus.OK).body(accountLoginResponse);
    }
}
