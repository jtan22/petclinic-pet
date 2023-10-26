package com.bw.petclinic.pet.controller;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(String message) {
        super(message);
    }

}
