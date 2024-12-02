package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.measurements.AbstractMeasure;
import ru.fastdelivery.domain.common.measurements.Height;
import ru.fastdelivery.domain.common.measurements.Length;
import ru.fastdelivery.domain.common.measurements.Width;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);

        Length length = new Length(BigInteger.ONE);
        Height height = new Height(BigInteger.ONE);
        Width width = new Width(BigInteger.ONE);

        var packages = List.of(new Pack(weight1, length, width, height), new Pack(weight2, length, width, height));
        var shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"));

        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }
}