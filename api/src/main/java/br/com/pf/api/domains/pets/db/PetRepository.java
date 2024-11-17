package br.com.pf.api.domains.pets.db;

import br.com.pf.api.domains.pets.enums.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {

    @Query("SELECT p FROM Pet p WHERE " +
            "(:name IS NULL OR p.name = :name) AND " +
            "(:type IS NULL OR p.type = :type) AND " +
            "(:breed IS NULL OR p.breed = :breed) AND " +
            "(:giverId IS NULL OR p.giver.accountId = :giverId)")
    Optional<List<Pet>> findPets(
            @Param("name") String name,
            @Param("type") PetType type,
            @Param("breed") String breed,
            @Param("giverId") UUID giverId
    );
}
