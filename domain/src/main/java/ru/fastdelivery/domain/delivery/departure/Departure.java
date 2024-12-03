package ru.fastdelivery.domain.delivery.departure;

/**
 * Основной класс места назначения
 * @param latitude широта
 * @param longitude долгота
 */
public record Departure(Double latitude, Double longitude) {

    public Departure{
        if(latitude == null || longitude == null){
            throw new IllegalArgumentException("impossible to apply current values");
        }
    }
}
