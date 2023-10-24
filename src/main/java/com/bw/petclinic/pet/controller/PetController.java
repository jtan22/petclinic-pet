package com.bw.petclinic.pet.controller;

import com.bw.petclinic.pet.domain.Pet;
import com.bw.petclinic.pet.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
