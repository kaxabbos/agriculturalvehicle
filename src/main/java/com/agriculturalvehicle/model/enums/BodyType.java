package com.agriculturalvehicle.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BodyType {
    VAN("Фургон"),
    MINI_TRUCK("Мини-грузовик"),
    TRUCK("Грузовик"),
    BIG_TRUCK("Большой грузовик"),
    TRACTOR("Трактор"),
    ;
    private final String name;
}

