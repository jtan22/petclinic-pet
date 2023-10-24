package com.bw.petclinic.pet.controller;

import com.bw.petclinic.pet.domain.Pet;
import com.bw.petclinic.pet.domain.PetType;
import com.bw.petclinic.pet.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PetController {

    private static final Logger LOG = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/")
    public String welcome() {
        LOG.info("GET /");
        return "welcome";
    }

    @GetMapping("/pets/names")
    public String getPetNames(@RequestParam("ownerId") int ownerId) {
        LOG.info("GET /pets/names with ownerId [" + ownerId + "]");
        return petRepository.findByOwnerId(ownerId).stream().map(Pet::getName).collect(Collectors.joining(", "));
    }

    @GetMapping("/pets/owner")
    public List<Pet> getPets(@RequestParam("ownerId") int ownerId) {
        LOG.info("GET /pets with ownerId [" + ownerId + "]");
        return petRepository.findByOwnerId(ownerId);
    }

    @GetMapping("/pets/types")
    public List<String> getPetTypes() {
        LOG.info("GET /pets/types");
        return petRepository.findPetTypes().stream().map(PetType::getName).toList();
    }

    @GetMapping("/pets/pet")
    public Pet getPet(@RequestParam("id") int id) {
        LOG.info("GET /pets with id [" + id + "]");
        return petRepository.findById(id).orElseGet(Pet::new);
    }

    @PostMapping("/pets/pet")
    public Pet savePet(@RequestBody Pet pet) {
        LOG.info("POST /pets/pet with Pet [" + pet + "]");
        return petRepository.save(pet);
    }

}
