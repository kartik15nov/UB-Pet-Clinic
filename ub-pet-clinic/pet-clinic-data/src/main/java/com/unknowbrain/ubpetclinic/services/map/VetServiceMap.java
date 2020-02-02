package com.unknowbrain.ubpetclinic.services.map;

import com.unknowbrain.ubpetclinic.model.Specialty;
import com.unknowbrain.ubpetclinic.model.Vet;
import com.unknowbrain.ubpetclinic.services.SpecialtyService;
import com.unknowbrain.ubpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet save(Vet object) {
        Objects.requireNonNull(object);

        if (object.getSpecialties().size() > 0) {
            object.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null) {
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
