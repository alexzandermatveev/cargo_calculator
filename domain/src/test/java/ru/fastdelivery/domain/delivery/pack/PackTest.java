package ru.fastdelivery.domain.delivery.pack;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.measurements.Height;
import ru.fastdelivery.domain.common.measurements.Length;
import ru.fastdelivery.domain.common.measurements.Width;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PackTest {

    @Test
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        Length length = new Length(BigInteger.valueOf(150));
        Width width = new Width(BigInteger.valueOf(150));
        Height height = new Height(BigInteger.valueOf(150));
        assertThatThrownBy(() -> new Pack(weight, length, width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWeightLessThanMaxWeight_thenObjectCreated() {
        var actual = new Pack(new Weight(BigInteger.valueOf(1_000)),
                new Length(BigInteger.valueOf(1_003)),
                new Width(BigInteger.valueOf(1_004)),
                new Height(BigInteger.valueOf(1_005)));

        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
        assertThat(actual.length()).isEqualTo(new Length(BigInteger.valueOf(1_003)));
        assertThat(actual.width()).isEqualTo(new Width(BigInteger.valueOf(1_004)));
        assertThat(actual.height()).isEqualTo(new Height(BigInteger.valueOf(1_005)));
    }
}