package ru.fastdelivery.domain.common.place;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.delivery.departure.Departure;
import ru.fastdelivery.domain.delivery.destination.Destination;
import ru.fastdelivery.domain.delivery.place.DistanceCalculator;

public class DistanceCalculatorTest {

    private final double lat1 = 77.1539;
    private final double long1 = -120.398;

    private final double lat2 = 77.1804;
    private final double long2 = 129.55;

    private final Departure departure = new Departure(lat1, long1);
    private final Destination destination = new Destination(lat2, long2);

    @Test
    @DisplayName("Тест расчета расстояния по поверхности сферы")
    public void calcDistanceTest() {
        double expected = 2332669;
        Assertions.assertEquals(expected, Math.round(DistanceCalculator.calcDistance(departure, destination)));
    }

}
