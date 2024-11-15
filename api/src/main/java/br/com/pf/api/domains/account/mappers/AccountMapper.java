package br.com.pf.api.domains.account.mappers;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.account.dto.AccountRequestDTO;
import br.com.pf.api.domains.account.dto.AccountResponseDTO;
import br.com.pf.api.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AccountMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account buildRequestToAccount(AccountRequestDTO request) {
        log.info("Building the object to be save");
        return Account
                .builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .avatar(request.avatar())
                .build();
    }

    public AccountResponseDTO buildAccountToAccountResponse(Account account) {
        log.info("Building the object to response");
        return new AccountResponseDTO(
                account.getName(),
                account.getEmail(),
                account.getAvatar()
        );
    }

    public Account buildAccountToUpdate(Account account, AccountRequestDTO accountRequest) {
        log.info("Building the object to be updated");
        var accountName = Utils.compareValues(account.getName(), accountRequest.name());
        var accountEmail = Utils.compareValues(account.getEmail(), accountRequest.email());
        var accountPassword = Utils.compareValues(account.getPassword(), accountRequest.password());
        var accountAvatar = Utils.compareValues(account.getAvatar(), accountRequest.avatar());
        return Account
                .builder()
                .accountId(account.getAccountId())
                .name(accountName.toString())
                .email(accountEmail.toString())
                .password(passwordEncoder.encode(accountPassword.toString()))
                .avatar((byte[]) accountAvatar)
                .build();
    }
}
