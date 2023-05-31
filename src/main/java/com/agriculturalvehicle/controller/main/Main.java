package com.agriculturalvehicle.controller.main;

import com.agriculturalvehicle.model.Users;
import com.agriculturalvehicle.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected CarsRepo carsRepo;
    @Autowired
    protected CarsDescriptionRepo carsDescriptionRepo;
    @Autowired
    protected ProductsRepo productsRepo;
    @Autowired
    protected TasksRepo tasksRepo;
    @Value("${upload.img}")
    protected String uploadImg;
    protected String def_employee = "def/def_employee.jpg";

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }
}