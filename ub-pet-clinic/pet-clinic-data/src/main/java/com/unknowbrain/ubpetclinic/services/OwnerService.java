package com.unknowbrain.ubpetclinic.services;

import com.unknowbrain.ubpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLatName(String lastName);

}
