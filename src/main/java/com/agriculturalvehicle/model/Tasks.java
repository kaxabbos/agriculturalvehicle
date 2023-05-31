package com.agriculturalvehicle.model;

import com.agriculturalvehicle.model.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tasks {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String photo;
    private String address;
    private double price;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Tasks(String name, String photo, String address, double price) {
        this.name = name;
        this.photo = photo;
        this.address = address;
        this.price = price;
        status = TaskStatus.WAITING;
    }
}
