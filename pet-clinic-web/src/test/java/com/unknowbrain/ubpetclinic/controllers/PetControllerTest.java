package com.unknowbrain.ubpetclinic.controllers;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.Pet;
import com.unknowbrain.ubpetclinic.model.PetType;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import com.unknowbrain.ubpetclinic.services.PetService;
import com.unknowbrain.ubpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    private static final String VIEW_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    List<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petTypes = new ArrayList<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void onButtonClickAddPetOpenCreationForm() throws Exception {
        //Since the below 2 service calls are defined in the @ModelAttributes in the controller
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(VIEW_CREATE_OR_UPDATE_PET_FORM));

        verify(ownerService).findById(anyLong());
    }

    @Test
    void onButtonClickAddSaveCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void onButtonClickEditPetOpenCreationForm() throws Exception {
        //Since the below 2 service calls are defined in the @ModelAttributes in the controller
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(VIEW_CREATE_OR_UPDATE_PET_FORM));

        verify(ownerService).findById(anyLong());
    }

    @Test
    void onButtonClickEditSaveCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }
}