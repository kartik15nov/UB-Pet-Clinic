package com.unknowbrain.ubpetclinic.bootstrap;

import com.unknowbrain.ubpetclinic.model.*;
import com.unknowbrain.ubpetclinic.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kalia");
        owner1.setLastName("Sahu");
        owner1.setAddress("Flat no 2, Nandita Apt.");
        owner1.setCity("Bangalore");
        owner1.setTelephone("+91 1234509876");

        Pet kaliaPet = new Pet();
        kaliaPet.setName("Kalia Kutta");
        kaliaPet.setPetType(saveDogPetType);
        kaliaPet.setOwner(owner1);
        kaliaPet.setBirthDate(LocalDate.now());

        owner1.getPets().add(kaliaPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kirana");
        owner2.setLastName("Sahu");
        owner2.setAddress("Flat no 3, Nandita Apt.");
        owner2.setCity("Bangalore");
        owner2.setTelephone("+91 1234567890");

        Pet dhalaPet = new Pet();
        dhalaPet.setName("Kalia Bilei");
        dhalaPet.setPetType(saveCatPetType);
        dhalaPet.setOwner(owner2);
        dhalaPet.setBirthDate(LocalDate.now());

        owner2.getPets().add(dhalaPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners..");


        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneeze Cat");
        catVisit.setPet(dhalaPet);
        visitService.save(catVisit);


        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");

        Vet vet1 = new Vet();
        vet1.setFirstName("Lopa");
        vet1.setLastName("Sahoo");
        vet1.getSpecialties().add(specialtyService.save(radiology));
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kanhu");
        vet2.setLastName("Sahoo");
        vet2.getSpecialties().add(specialtyService.save(surgery));
        vet2.getSpecialties().add(specialtyService.save(dentistry));
        vetService.save(vet2);

        System.out.println("Loaded Vets..");
    }
}
