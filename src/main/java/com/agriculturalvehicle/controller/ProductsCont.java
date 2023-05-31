package com.agriculturalvehicle.controller;

import com.agriculturalvehicle.controller.main.Attributes;
import com.agriculturalvehicle.model.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsCont extends Attributes {

    @GetMapping
    public String products(Model model) {
        AddAttributesProducts(model);
        return "products";
    }

    @PostMapping("/buy/{id}")
    public String productBuy(@RequestParam int quantity, @PathVariable Long id) {
        Products product = productsRepo.getReferenceById(id);
        product.setQuantity(product.getQuantity() + quantity);
        productsRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String productAdd(Model model) {
        AddAttributes(model);
        return "product_add";
    }

    @PostMapping("/add")
    public String productAdd(@RequestParam String name, @RequestParam double price) {
        productsRepo.save(new Products(name, price));
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String productEdit(Model model, @PathVariable Long id) {
        AddAttributesProductEdit(model, id);
        return "product_edit";
    }

    @PostMapping("/edit/{id}")
    public String productEdit(@PathVariable Long id, @RequestParam String name, @RequestParam double price) {
        Products product = productsRepo.getReferenceById(id);
        product.setName(name);
        product.setPrice(price);
        productsRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable Long id) {
        productsRepo.deleteById(id);
        return "redirect:/products";
    }


}
