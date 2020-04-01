package com.unknowbrain.ubpetclinic.services.springdatajpa;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.repositories.OwnerRepository;
import com.unknowbrain.ubpetclinic.repositories.PetRepository;
import com.unknowbrain.ubpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    public static final String LAST_NAME = "Mallik";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJPAService service;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(5L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLatName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        Owner owner = service.findByLatName(LAST_NAME);

        assertEquals("Mallik", owner.getLastName());

        verify(ownerRepository).findByLastName(anyString());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner owner = service.findById(5L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(5L);

        assertNull(owner);
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwners = new HashSet<>();
        returnedOwners.add(Owner.builder().id(3L).lastName("Mallik").build());
        returnedOwners.add(Owner.builder().id(4L).lastName("Sahu").build());

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        List<Owner> owners = service.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner ownerToSave = Owner.builder().id(6L).lastName("Sahoo").build();
        Owner owner = service.save(ownerToSave);

        assertNotNull(owner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedOwner);

        verify(ownerRepository).delete(returnedOwner);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLatNameLike() {
        //given
        Owner owner1 = Owner.builder().id(1L).lastName("Sahoo").build();
        Owner owner2 = Owner.builder().id(2L).lastName("Sah").build();

        ArrayList<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAllByLastNameLike(anyString())).thenReturn(owners);

        //when
        List<Owner> list = service.findAllByLastNameLike("Sah");

        //then
        assertEquals(owners.size(), list.size());
    }
}