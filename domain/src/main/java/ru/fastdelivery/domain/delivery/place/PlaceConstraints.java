package ru.fastdelivery.domain.delivery.place;

public interface PlaceConstraints {
    boolean isLatitudeOutOfLimit(Double latitude);

    boolean isLongitudeOutOfLimit(Double longitude);
}
