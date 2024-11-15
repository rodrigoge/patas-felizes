package br.com.pf.api.domains.account.services;

import br.com.pf.api.domains.account.db.AccountRepository;
import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.domains.account.mappers.AccountMapper;
import br.com.pf.api.exceptions.GenericException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

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
}
