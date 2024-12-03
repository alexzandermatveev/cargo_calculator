package ru.fastdelivery.domain.common.volume;

import ru.fastdelivery.domain.common.measurements.Height;
import ru.fastdelivery.domain.common.measurements.Length;
import ru.fastdelivery.domain.common.measurements.Width;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Общий класс объема
 */
public record Volume(BigDecimal volume) {


    public Volume {
        if (BigDecimal.ZERO.compareTo(volume) > 0) {
            throw new IllegalArgumentException("Volume cannot be below Zero!");
        }
    }

    public static Volume getZeroVolume() {
        return new Volume(BigDecimal.ZERO);
    }

    public Volume addVolume(Volume additional) {
        return new Volume(volume.add(additional.volume));
    }


    /**
     * Расчет объема груза
     *
     * @return объем груза
     */
    public static Volume countVolume(Length length, Width width, Height height) {
        return new Volume(new BigDecimal(length.millimeters()
                .multiply(width.millimeters())
                .multiply(height.millimeters()))
                .divide(BigDecimal.valueOf(1_000_000_000), 10_000, RoundingMode.HALF_UP));
    }
}
