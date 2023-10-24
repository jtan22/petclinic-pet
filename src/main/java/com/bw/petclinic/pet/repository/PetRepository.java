package com.bw.petclinic.pet.repository;

import com.bw.petclinic.pet.domain.Pet;
import com.bw.petclinic.pet.domain.PetType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

    List<Pet> findByOwnerId(int ownerId);

    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    List<PetType> findPetTypes();

}
