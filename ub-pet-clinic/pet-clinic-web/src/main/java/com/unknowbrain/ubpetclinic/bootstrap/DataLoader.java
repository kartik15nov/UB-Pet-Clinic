package com.unknowbrain.ubpetclinic.bootstrap;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.PetType;
import com.unknowbrain.ubpetclinic.model.Vet;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import com.unknowbrain.ubpetclinic.services.PetTypeService;
import com.unknowbrain.ubpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        
        Owner owner1 = new Owner();
        owner1.setFirstName("Kalia");
        owner1.setLastName("Sahu");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kirana");
        owner2.setLastName("Sahu");

        ownerService.save(owner2);

        System.out.println("Loaded Owners..");

        Vet vet1 = new Vet();
        vet1.setFirstName("Lopa");
        vet1.setLastName("Sahoo");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kanhu");
        vet2.setLastName("Sahoo");

        vetService.save(vet2);

        System.out.println("Loaded Vets..");
    }
}
