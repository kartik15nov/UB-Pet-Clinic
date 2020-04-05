package com.unknowbrain.ubpetclinic.controllers;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.Pet;
import com.unknowbrain.ubpetclinic.model.PetType;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import com.unknowbrain.ubpetclinic.services.PetService;
import com.unknowbrain.ubpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEW_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String addNewPet(Owner owner, Model model) {

        model.addAttribute("pet", Pet.builder().owner(owner).build());

        return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String saveNewPet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.addPet(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_CREATE_OR_UPDATE_PET_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String editPet(@PathVariable Long petId, Model model) {

        model.addAttribute("pet", petService.findById(petId));

        return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String saveEditedPet(@PathVariable Long petId, Owner owner, @Valid Pet pet, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_CREATE_OR_UPDATE_PET_FORM;
        } else {
            owner.addPet(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
