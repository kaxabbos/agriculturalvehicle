package com.agriculturalvehicle.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cars {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String[] photos;
    private boolean free;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarsDescription description;

    public Cars(String name,  boolean free, String[] photos) {
        this.name = name;
        this.free = free;
        this.photos = photos;
    }
}
