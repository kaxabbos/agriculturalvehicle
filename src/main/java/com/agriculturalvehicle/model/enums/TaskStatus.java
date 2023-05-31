package com.agriculturalvehicle.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    WAITING("В ожидании"),
    IN_PROGRESS("В процессе"),
    DONE("Выполнено"),
    ;
    private final String name;
}

