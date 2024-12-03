package ru.fastdelivery.properties.provider;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.fastdelivery.domain.delivery.place.PlaceConstraints;

/**
 * Настройка ограничений для координат
 */
@ConfigurationProperties("space")
@Data
public class CoordinatesLimitProperties implements PlaceConstraints {
    private Double latitudeMin;
    private Double latitudeMax;
    private Double longitudeMin;
    private Double longitudeMax;


    @Override
    public boolean isLatitudeOutOfLimit(Double latitude) {
        return latitude.compareTo(latitudeMax) > 0 || latitude.compareTo(latitudeMin) < 0;
    }

    @Override
    public boolean isLongitudeOutOfLimit(Double longitude) {
        return longitude.compareTo(longitudeMax) > 0 || longitude.compareTo(longitudeMin) < 0;
    }

}
