package com.unknowbrain.ubpetclinic.repositories;

import com.unknowbrain.ubpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
