package ru.fastdelivery.domain.delivery.place;

import ru.fastdelivery.domain.delivery.departure.Departure;
import ru.fastdelivery.domain.delivery.destination.Destination;

/**
 * Класс для расчета расстояния между точками на поверхности сферы
 * <a href="https://gis-lab.info/qa/great-circles.html">article with formulas</a>
 */
public class DistanceCalculator {
    /**
     * Радиус сферы (Земли)
     */
    private static final Double EARTH_RAD = 6372795.0;

    public static Double calcDistance(Departure departure, Destination destination) {
        // в радианах
        final double depLatRad = departure.latitude() * Math.PI / 180;
        final double depLongRad = departure.longitude() * Math.PI / 180;
        final double destLatRad = destination.latitude() * Math.PI / 180;
        final double destLongRad = destination.longitude() * Math.PI / 180;

        //косинусы и синусы широт и разницы долгот
        double cosLatDep = Math.cos(depLatRad);
        double cosLatDest = Math.cos(destLatRad);
        double sinLatDep = Math.sin(depLatRad);
        double sinLatDest = Math.sin(destLatRad);
        double delta = depLongRad - destLongRad;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        //вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cosLatDest * sinDelta, 2) +
                Math.pow(cosLatDep * sinLatDest - sinLatDep * cosLatDest * cosDelta, 2));
        double x = sinLatDep * sinLatDest + cosLatDep * cosLatDest * cosDelta;
        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RAD;

        return dist;
    }
}
