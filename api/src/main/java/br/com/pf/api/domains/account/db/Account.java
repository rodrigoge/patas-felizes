package br.com.pf.api.domains.account.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "E-mail cannot be empty")
    private String email;

    @Column(length = 100)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column
    private byte[] avatar;
}
