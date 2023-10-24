package com.bw.petclinic.pet.repository;

import com.bw.petclinic.pet.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

    List<Pet> findByOwnerId(int ownerId);

}
