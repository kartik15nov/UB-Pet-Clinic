package com.unknowbrain.ubpetclinic.controllers;

import com.unknowbrain.ubpetclinic.model.Owner;
import com.unknowbrain.ubpetclinic.model.Pet;
import com.unknowbrain.ubpetclinic.model.PetType;
import com.unknowbrain.ubpetclinic.services.PetService;
import com.unknowbrain.ubpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    HashMap<String, String> uriMap = new HashMap<>();
    UriTemplate visitUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    URI visitUri;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;

        when(petService.findById(anyLong()))
                .thenReturn(Pet.builder()
                        .id(petId)
                        .birthDate(LocalDate.of(2018, 11, 30))
                        .name("Shampoo")
                        .visits(new ArrayList<>())
                        .owner(Owner.builder()
                                .id(ownerId)
                                .lastName("Sahu")
                                .firstName("Kalia")
                                .build())
                        .petType(PetType.builder().name("Dog").build())
                        .build());

        uriMap.clear();
        uriMap.put("ownerId", ownerId.toString());
        uriMap.put("petId", petId.toString());

        visitUri = visitUriTemplate.expand(uriMap);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2018-11-11")
                .param("description", "Yet another visit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));
    }
}