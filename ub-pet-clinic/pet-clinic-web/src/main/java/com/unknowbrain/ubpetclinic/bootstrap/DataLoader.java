package com.unknowbrain.ubpetclinic.bootstrap;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.Vet;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import com.unknowbrain.ubpetclinic.services.VetService;
import com.unknowbrain.ubpetclinic.services.map.OwnerServiceMap;
import com.unknowbrain.ubpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Kalia");
        owner1.setLastName("Sahu");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Kirana");
        owner1.setLastName("Sahu");

        ownerService.save(owner2);

        System.out.println("Loaded Owners..");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Lopa");
        vet1.setLastName("Sahoo");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Kanhu");
        vet1.setLastName("Sahoo");

        vetService.save(vet2);

        System.out.println("Loaded Vets..");
    }
}
