package ru.fastdelivery.domain.common.measurements;

import java.math.BigInteger;

/**
 * Общий класс длины
 *
 * @param millimeters длина в миллиметрах
 */
public record Length(BigInteger millimeters) implements AbstractMeasure<Length> {

    public Length {
        if (isLessThanZero(millimeters)) {
            throw new IllegalArgumentException("Length cannot be below Zero!");
        }
        if (isMoreThanLimit(millimeters)) {
            throw new IllegalArgumentException("Length cannot be more than 1500 mm");
        }
        millimeters = roundToMultipleOfFifty(millimeters);
    }


    @Override
    public int compareTo(Length o) {
        return o.millimeters().compareTo(millimeters());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Length length = (Length) obj;
        return millimeters.compareTo(length.millimeters) == 0;
    }
}
