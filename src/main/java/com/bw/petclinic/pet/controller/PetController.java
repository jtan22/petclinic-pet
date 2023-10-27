package com.bw.petclinic.pet.controller;

import com.bw.petclinic.pet.domain.Pet;
import com.bw.petclinic.pet.domain.PetType;
import com.bw.petclinic.pet.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PetController {

    private static final Logger LOG = LoggerFactory.getLogger(PetController.class);

    private final PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Health check.
     *
     * @return
     */
    @GetMapping("/")
    public String welcome() {
        LOG.info("GET /");
        return "welcome";
    }

    /**
     * Get all pets' names for a given ownerId.
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/pets/names")
    public String getPetNames(@RequestParam("ownerId") int ownerId) {
        LOG.info("GET /pets/names with ownerId [" + ownerId + "]");
        return getPets(ownerId).stream().
                map(Pet::getName).
                collect(Collectors.joining(", "));
    }

    /**
     * Get all pets for a given ownerId.
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/pets/owner")
    public List<Pet> getPets(@RequestParam("ownerId") int ownerId) {
        LOG.info("GET /pets with ownerId [" + ownerId + "]");
        return petRepository.findByOwnerId(ownerId);
    }

    /**
     * Get all PetTypes.
     *
     * @return
     */
    @GetMapping("/pets/types")
    public List<PetType> getPetTypes() {
        LOG.info("GET /pets/types");
        return petRepository.findPetTypes();
    }

    /**
     * Get a single Pet by its id.
     *
     * @param id
     * @return
     */
    @GetMapping("/pets/pet")
    public Pet getPet(@RequestParam("id") int id) {
        LOG.info("GET /pets with id [" + id + "]");
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return pet.get();
        }
        throw new PetNotFoundException("Pet [" + id + "] not found");
    }

    /**
     * Save a single Pet.
     *
     * @param pet
     * @return
     */
    @PostMapping("/pets/pet")
    public Pet savePet(@RequestBody Pet pet) {
        LOG.info("POST /pets/pet with Pet [" + pet + "]");
        return petRepository.save(pet);
    }

}
