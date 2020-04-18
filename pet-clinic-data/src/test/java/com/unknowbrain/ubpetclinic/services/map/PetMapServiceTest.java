package com.unknowbrain.ubpetclinic.services.map;

import com.unknowbrain.ubpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetMapServiceTest {

    private final Long petId = 1L;
    private PetMapService petMapService;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdWhereIdNotExist() {
        Pet pet = petMapService.findById(5L);
        assertNull(pet);
    }

    @Test
    void findByIdWhereIdIsNull() {
        Pet pet = petMapService.findById(null);
        assertNull(pet);
    }

    @Test
    void findAll() {
        List<Pet> petList = petMapService.findAll();

        assertEquals(1, petList.size());
    }

    @Test
    void save() {
        Pet pet = petMapService.save(Pet.builder().id(2L).build());

        assertEquals(2L, pet.getId());
    }

    @Test
    void delete() {
        Pet pet = petMapService.findById(1L);

        petMapService.delete(pet);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(1L);

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByNotExistId() {
        petMapService.deleteById(5L);

        assertEquals(1, petMapService.findAll().size());
    }
    @Test
    void deleteByNullId() {
        petMapService.deleteById(5L);

        assertEquals(1, petMapService.findAll().size());
    }

}