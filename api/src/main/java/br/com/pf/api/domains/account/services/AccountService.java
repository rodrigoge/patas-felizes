package br.com.pf.api.domains.account.services;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.account.db.AccountRepository;
import br.com.pf.api.domains.account.dto.AccountLoginResponseDTO;
import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.account.dto.ResetPasswordRequestDTO;
import br.com.pf.api.domains.account.mappers.AccountMapper;
import br.com.pf.api.exceptions.GenericException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequest) {
        log.info("Starting the create account flow");
        var accountEmailExists = accountRepository.findByEmail(accountRequest.email());
        if (accountEmailExists.isPresent()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "This e-mail already in use");
        }
        var account = accountMapper.buildRequestToAccount(accountRequest);
        var accountSaved = accountRepository.save(account);
        var accountResponse = accountMapper.buildAccountToAccountResponse(accountSaved);
        log.info("Finishing the create account flow");
        return accountResponse;
    }

    @Transactional
    public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO accountRequest) {
        log.info("Starting the update account flow");
        var accountFounded = accountRepository.findById(accountId);
        if (accountFounded.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "This account doesn't exists");
        }
        if (!Objects.equals(accountFounded.get().getEmail(), accountRequest.email())) {
            var accountEmailExists = accountRepository.findByEmail(accountRequest.email());
            if (accountEmailExists.isPresent()) {
                throw new GenericException(HttpStatus.BAD_REQUEST, "This e-mail already in use");
            }
        }
        var account = accountMapper.buildAccountToUpdate(accountFounded.get(), accountRequest);
        var accountSaved = accountRepository.save(account);
        var accountResponse = accountMapper.buildAccountToAccountResponse(accountSaved);
        log.info("Finishing the update account flow");
        return accountResponse;
    }

    @Transactional
    public AccountResponseDTO updatePassword(String token, ResetPasswordRequestDTO resetPasswordRequest) {
        log.info("Starting the update password flow");
        var accountEmail = tokenService.verifyToken(token);
        if (ObjectUtils.isEmpty(accountEmail)) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "This token is invalid");
        }
        var accountFounded = accountRepository.findByEmail(resetPasswordRequest.email()).orElseThrow(() ->
                new GenericException(HttpStatus.BAD_REQUEST, "This e-mail doesn't exists")
        );
        if (ObjectUtils.isEmpty(accountFounded)) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "This e-mail already in use");
        }
        var accountRequest = new AccountRequestDTO(
                accountFounded.getName(),
                accountFounded.getEmail(),
                resetPasswordRequest.password(),
                accountFounded.getAvatar()
        );
        var account = accountMapper.buildAccountToUpdate(accountFounded, accountRequest);
        var accountSaved = accountRepository.save(account);
        var accountResponse = accountMapper.buildAccountToAccountResponse(accountSaved);
        log.info("Finishing the update password flow");
        return accountResponse;
    }

    public AccountLoginResponseDTO login(Authentication authenticate) {
        log.info("Starting the login flow");
        var account = (Account) authenticate.getPrincipal();
        var accountResponse = accountMapper.buildAccountToAccountResponse(account);
        var token = tokenService.generateToken(account.getEmail());
        var accountLoginResponse = new AccountLoginResponseDTO(accountResponse, token);
        log.info("Finishing the login flow");
        return accountLoginResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username).orElseThrow(() -> new GenericException(
                HttpStatus.BAD_REQUEST,
                "This account doesn't exists")
        );
    }
}
