package ru.fastdelivery.domain.common.measurements;

import java.math.BigInteger;

/**
 * Общий класс ширины
 *
 * @param millimeters ширина в миллиметрах
 */
public record Width(BigInteger millimeters) implements AbstractMeasure<Width> {

    public Width {
        if (isLessThanZero(millimeters)) {
            throw new IllegalArgumentException("Width cannot be below Zero!");
        }
        if (isMoreThanLimit(millimeters)) {
            throw new IllegalArgumentException("Width cannot be more than 1500 mm");
        }
        millimeters = roundToMultipleOfFifty(millimeters);
    }

    @Override
    public int compareTo(Width o) {
        return o.millimeters().compareTo(millimeters());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Width width = (Width) obj;
        return millimeters.compareTo(width.millimeters) == 0;
    }
}
