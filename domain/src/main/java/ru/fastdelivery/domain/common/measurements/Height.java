package ru.fastdelivery.domain.common.measurements;

import java.math.BigInteger;

/**
 * Общий класс высоты
 *
 * @param millimeters высота в миллиметрах
 */
public record Height(BigInteger millimeters) implements AbstractMeasure<Height> {

    public Height {
        if (isLessThanZero(millimeters)) {
            throw new IllegalArgumentException("Height cannot be below Zero!");
        }
        if (isMoreThanLimit(millimeters)) {
            throw new IllegalArgumentException("Height cannot be more than 1500 mm");
        }
    }

    @Override
    public int compareTo(Height o) {
        return o.millimeters().compareTo(millimeters());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Height height = (Height) obj;
        return millimeters.compareTo(height.millimeters) == 0;
    }
}
