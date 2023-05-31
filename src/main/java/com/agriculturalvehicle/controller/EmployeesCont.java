package com.agriculturalvehicle.controller;

import com.agriculturalvehicle.controller.main.Attributes;
import com.agriculturalvehicle.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/employees")
public class EmployeesCont extends Attributes {

    @GetMapping
    public String employees(Model model) {
        AddAttributesEmployees(model);
        return "employees";
    }

    @GetMapping("/edit/{id}")
    public String employeeEdit(Model model, @PathVariable Long id) {
        AddAttributesEmployeeEdit(model, id);
        return "employee_edit";
    }

    @PostMapping("/edit/{id}")
    public String employeeEdit(Model model, @RequestParam String fio, @RequestParam MultipartFile file, @RequestParam String post, @PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "tasks/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesEmployeeEdit(model, id);
                return "employee_edit";
            }
            user.setPhoto(res);
        }

        user.setFio(fio);
        user.setPost(post);

        usersRepo.save(user);

        return "redirect:/employees";
    }

}
