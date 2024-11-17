package br.com.pf.api.domains.pets.services;

import br.com.pf.api.domains.account.db.AccountRepository;
import br.com.pf.api.domains.pets.db.Pet;
import br.com.pf.api.domains.pets.db.PetRepository;
import br.com.pf.api.domains.pets.dto.CreatePetRequestDTO;
import br.com.pf.api.domains.pets.dto.GetPetsRequestDTO;
import br.com.pf.api.domains.pets.dto.PetResponseDTO;
import br.com.pf.api.domains.pets.enums.PetType;
import br.com.pf.api.domains.pets.mappers.PetMapper;
import br.com.pf.api.exceptions.GenericException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public PetResponseDTO createPet(UUID accountId, CreatePetRequestDTO createPetRequest) {
        log.info("Starting the create pet flow");
        var account = accountRepository.findById(accountId).orElseThrow(() -> new GenericException(
                HttpStatus.BAD_REQUEST, "This giver don't exists"
        ));
        var pet = petMapper.buildRequestToPet(createPetRequest, account, null);
        var petSaved = petRepository.save(pet);
        var petResponse = petMapper.buildPetToPetResponse(petSaved);
        log.info("Finishing the create pet flow");
        return petResponse;
    }

    public List<PetResponseDTO> findPets(GetPetsRequestDTO getPetsRequest) {
        log.info("Starting the get pets flow");
        var petSpecification = findPets(
                getPetsRequest.name(),
                getPetsRequest.type(),
                getPetsRequest.breed(),
                getPetsRequest.giverId()
        );
        var pets = petRepository.findAll(petSpecification);
        if (pets.isEmpty()) return List.of();
        var petsResponse = pets
                .stream()
                .map(pet -> petMapper.buildPetToPetResponse(pet))
                .toList();
        log.info("Finishing the get pets flow");
        return petsResponse;
    }

    public static Specification<Pet> findPets(String name, PetType type, String breed, UUID giverId) {
        return ((root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();
            if (name != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (type != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), type));
            }
            if (breed != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("breed")), "%" + breed.toLowerCase() + "%"));
            }
            if (giverId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("giver").get("accountId"), giverId));
            }
            return predicate;
        });
    }

    @Transactional
    public PetResponseDTO updatePet(UUID petId, CreatePetRequestDTO petRequest) {
        log.info("Starting the update pet flow");
        var petFounded = petRepository.findById(petId);
        if (petFounded.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "This pet doesn't exists");
        }
        var pet = petMapper.buildRequestToPet(petRequest, petFounded.get().getGiver(), petFounded.get().getReceiver());
        var petSaved = petRepository.save(pet);
        var petResponse = petMapper.buildPetToPetResponse(petSaved);
        log.info("Finishing the update pet flow");
        return petResponse;
    }
}
