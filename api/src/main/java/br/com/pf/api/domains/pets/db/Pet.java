package br.com.pf.api.domains.pets.db;

import br.com.pf.api.domains.account.db.Account;
import br.com.pf.api.domains.pets.enums.PetType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "pet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID petId;

    @Column
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column
    @NotBlank(message = "Address cannot be empty")
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private PetType type;

    @Column
    @NotBlank(message = "Age cannot be empty")
    private Integer age;

    @Column
    @NotBlank(message = "Breed cannot be empty")
    private String breed;

    @Column
    private byte[] avatar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "giver_id", referencedColumnName = "account_id")
    private Account giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "account_id")
    private Account receiver;
}
