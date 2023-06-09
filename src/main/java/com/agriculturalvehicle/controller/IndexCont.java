package com.agriculturalvehicle.controller;

import com.agriculturalvehicle.controller.main.Attributes;
import com.agriculturalvehicle.model.enums.BodyType;
import com.agriculturalvehicle.model.enums.Fuel;
import com.agriculturalvehicle.model.enums.Transmission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam BodyType bodyType, @RequestParam Fuel fuel, @RequestParam Transmission transmission) {
        AddAttributesCatalogSearch(model, bodyType, fuel, transmission);
        return "catalog";
    }
}
