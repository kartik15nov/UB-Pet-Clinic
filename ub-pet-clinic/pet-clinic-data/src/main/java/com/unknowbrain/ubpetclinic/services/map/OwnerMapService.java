package com.unknowbrain.ubpetclinic.services.map;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.PetType;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import com.unknowbrain.ubpetclinic.services.PetService;
import com.unknowbrain.ubpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private PetService petService;
    private PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(Owner owner) {

        Objects.requireNonNull(owner);

        if (owner.getPets() != null) {
            owner.getPets().forEach(pet -> {
                PetType petType = pet.getPetType();
                if (petType != null) {
                    if (petType.getId() != null)
                        pet.setPetType(petTypeService.save(petType));
                } else
                    throw new RuntimeException("Pet Type is required");

                if (pet.getId() == null)
                    pet.setId(petService.save(pet).getId());
            });
        }

        return super.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLatName(String lastName) {
        return null;
    }
}
