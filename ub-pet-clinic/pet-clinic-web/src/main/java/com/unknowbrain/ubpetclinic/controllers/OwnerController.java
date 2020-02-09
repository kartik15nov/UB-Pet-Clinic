package com.unknowbrain.ubpetclinic.controllers;

import com.unknowbrain.ubpetclinic.model.BaseEntity;
import com.unknowbrain.ubpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.stream.Collectors;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("ownersList", ownerService.findAll().stream().sorted(Comparator.comparing(BaseEntity::getId)).collect(Collectors.toList()));
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "notimplemented";
    }
}
