package com.unknowbrain.ubpetclinic.services;

import com.unknowbrain.ubpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLatName(String lastName);

    List<Owner> findByLatNameLike(String s);
}
