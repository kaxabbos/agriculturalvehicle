package com.agriculturalvehicle.controller;

import com.agriculturalvehicle.controller.main.Attributes;
import com.agriculturalvehicle.model.Tasks;
import com.agriculturalvehicle.model.Users;
import com.agriculturalvehicle.model.enums.TaskStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TasksCont extends Attributes {

    @GetMapping
    public String tasks(Model model) {
        AddAttributesTasks(model);
        return "tasks";
    }

    @GetMapping("/my")
    public String tasksMy(Model model) {
        AddAttributesTasksMy(model);
        return "tasks_my";
    }

    @GetMapping("/add")
    public String taskAdd(Model model) {
        AddAttributes(model);
        return "task_add";
    }

    @GetMapping("/work/{id}")
    public String taskWork(@PathVariable Long id) {
        Tasks task = tasksRepo.getReferenceById(id);
        Users user = getUser();
        task.setStatus(TaskStatus.IN_PROGRESS);
        user.addTask(task);
        tasksRepo.save(task);
        usersRepo.save(user);
        return "redirect:/tasks";
    }

    @GetMapping("/done/{id}")
    public String taskDone(@PathVariable Long id) {
        Tasks task = tasksRepo.getReferenceById(id);
        task.setStatus(TaskStatus.DONE);
        tasksRepo.save(task);
        return "redirect:/tasks/my";
    }

    @GetMapping("/edit/{id}")
    public String taskEdit(Model model, @PathVariable Long id) {
        AddAttributesTaskEdit(model, id);
        return "task_edit";
    }

    @GetMapping("/delete/{id}")
    public String taskDelete(@PathVariable Long id) {
        tasksRepo.deleteById(id);
        return "redirect:/tasks";
    }

    @PostMapping("/add")
    public String taskAdd(Model model, @RequestParam String name, @RequestParam MultipartFile file, @RequestParam String address, @RequestParam double price) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
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
                AddAttributes(model);
                return "task_add";
            }
        }

        tasksRepo.save(new Tasks(name, res, address, price));

        return "redirect:/tasks";
    }

    @PostMapping("/edit/{id}")
    public String taskEdit(Model model, @RequestParam String name, @RequestParam MultipartFile file, @RequestParam String address, @RequestParam double price, @PathVariable Long id) {
        Tasks task = tasksRepo.getReferenceById(id);
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
                AddAttributesTaskEdit(model, id);
                return "task_edit";
            }
            task.setPhoto(res);
        }

        task.setName(name);
        task.setAddress(address);
        task.setPrice(price);

        tasksRepo.save(task);

        return "redirect:/tasks";
    }

}
