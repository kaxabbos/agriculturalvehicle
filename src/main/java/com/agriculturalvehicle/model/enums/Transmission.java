package com.agriculturalvehicle.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Transmission {
    AKPP("АКПП"),
    MKPP("МКПП");
    private final String name;
}

