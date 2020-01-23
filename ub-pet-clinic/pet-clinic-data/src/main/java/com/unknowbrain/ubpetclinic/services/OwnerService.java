package com.unknowbrain.ubpetclinic.services;

import com.unknowbrain.ubpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLatName(String lastName);

    Owner findById(Long id);

    Set<Owner> findAll();

    Owner save(Owner owner);
}
