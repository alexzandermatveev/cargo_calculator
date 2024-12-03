package ru.fastdelivery.domain.delivery.place;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.delivery.departure.Departure;
import ru.fastdelivery.domain.delivery.destination.Destination;

@RequiredArgsConstructor
public class PlaceFactory {
    private final PlaceConstraints placeConstraints;

    public Destination createDestination(Double latitude, Double longitude) {
        checkLimits(latitude, longitude);
        return new Destination(latitude, longitude);
    }

    public Departure createDeparture(Double latitude, Double longitude) {
        checkLimits(latitude, longitude);
        return new Departure(latitude, longitude);
    }

    private void checkLimits(Double latitude, Double longitude) {
        if (placeConstraints.isLatitudeOutOfLimit(latitude)) {
            throw new IllegalArgumentException("not available value for latitude");
        }
        if (placeConstraints.isLongitudeOutOfLimit(longitude)) {
            throw new IllegalArgumentException("not available value for longitude");
        }
    }

}
