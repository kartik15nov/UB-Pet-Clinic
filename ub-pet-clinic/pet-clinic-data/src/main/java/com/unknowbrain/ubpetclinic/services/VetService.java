package com.unknowbrain.ubpetclinic.services;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Set<Vet> findAll();

    Owner save(Vet vet);
}
