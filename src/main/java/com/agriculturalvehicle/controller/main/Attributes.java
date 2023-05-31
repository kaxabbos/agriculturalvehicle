package com.agriculturalvehicle.controller.main;

import com.agriculturalvehicle.model.Tasks;
import com.agriculturalvehicle.model.enums.*;
import org.springframework.ui.Model;

import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("bodyTypes", BodyType.values());
        model.addAttribute("fuels", Fuel.values());
        model.addAttribute("transmissions", Transmission.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesProducts(Model model) {
        AddAttributes(model);
        model.addAttribute("products", productsRepo.findAll());
    }

    protected void AddAttributesTasks(Model model) {
        AddAttributes(model);
        List<Tasks> tasks = tasksRepo.findAllByStatus(TaskStatus.WAITING);
        tasks.addAll(tasksRepo.findAllByStatus(TaskStatus.IN_PROGRESS));
        tasks.addAll(tasksRepo.findAllByStatus(TaskStatus.DONE));
        model.addAttribute("tasks", tasks);
    }

    protected void AddAttributesEmployees(Model model) {
        AddAttributes(model);
        model.addAttribute("employees", usersRepo.findAllByRole(Role.USER));
    }

    protected void AddAttributesTasksMy(Model model) {
        AddAttributes(model);
        model.addAttribute("tasks", getUser().getTasks());
    }

    protected void AddAttributesTaskEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("task", tasksRepo.getReferenceById(id));
    }

    protected void AddAttributesEmployeeEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("employee", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesProductEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("product", productsRepo.getReferenceById(id));
    }

    protected void AddAttributesCar(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("car", carsRepo.getReferenceById(id));
    }

    protected void AddAttributesCarsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("cars", getUser().getCars());
    }

    protected void AddAttributesCarAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesCarEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("car", carsRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("cars", carsRepo.findAllByOrderByFreeDesc());
    }

    protected void AddAttributesCatalogSearch(Model model, BodyType bodyType, Fuel fuel, Transmission transmission) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", bodyType);
        model.addAttribute("fSelect", fuel);
        model.addAttribute("tSelect", transmission);
        model.addAttribute("cars", carsRepo.findAllByDescription_BodyTypeAndDescription_FuelAndDescription_TransmissionOrderByFreeDesc(bodyType, fuel, transmission));
    }

    protected void AddAttributesStatistics(Model model) {
        AddAttributes(model);

        model.addAttribute("stats", usersRepo.findAllByRole(Role.USER));
    }
}
