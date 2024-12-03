package ru.fastdelivery.domain.delivery.destination;

import lombok.NonNull;

/**
 * Основной класс пункта назначения
 * @param latitude широта
 * @param longitude долгота
 */
public record Destination(Double latitude, Double longitude) {

    public Destination{

        if(latitude == null || longitude == null){
            throw new IllegalArgumentException("impossible to apply current values");
        }
    }
}

