package ru.fastdelivery.usecase;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.measurements.Height;
import ru.fastdelivery.domain.common.measurements.Length;
import ru.fastdelivery.domain.common.measurements.Width;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TariffCalculateUseCaseTest {

    final WeightPriceProvider weightPriceProvider = mock(WeightPriceProvider.class);
    final Currency currency = new CurrencyFactory(code -> true).create("RUB");

    final TariffCalculateUseCase tariffCalculateUseCase = new TariffCalculateUseCase(weightPriceProvider);

    @Test
    @DisplayName("Расчет стоимости доставки -> успешно")
    void whenCalculatePrice_thenSuccess() {
        var minimalPrice = new Price(BigDecimal.TEN, currency);
        var pricePerKg = new Price(BigDecimal.valueOf(100), currency);
        var pricePerMeter = new Price(BigDecimal.valueOf(100), currency);

        when(weightPriceProvider.minimalPrice()).thenReturn(minimalPrice);
        when(weightPriceProvider.costPerMeter()).thenReturn(pricePerMeter);
        when(weightPriceProvider.costPerKg()).thenReturn(pricePerKg);

        Length length = new Length(BigInteger.valueOf(150));
        Width width = new Width(BigInteger.valueOf(150));
        Height height = new Height(BigInteger.valueOf(150));
        Weight weight = new Weight(BigInteger.valueOf(1200));

        var shipment = new Shipment(List.of(new Pack(weight, length, width, height)),
                new CurrencyFactory(code -> true).create("RUB"));
        var expectedPrice = new Price(BigDecimal.valueOf(120), currency);

        var actualPrice = tariffCalculateUseCase.calc(shipment);

        assertThat(actualPrice).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("Получение минимальной стоимости -> успешно")
    void whenMinimalPrice_thenSuccess() {
        BigDecimal minimalValue = BigDecimal.TEN;
        var minimalPrice = new Price(minimalValue, currency);
        when(weightPriceProvider.minimalPrice()).thenReturn(minimalPrice);

        var actual = tariffCalculateUseCase.minimalPrice();

        assertThat(actual).isEqualTo(minimalPrice);
    }
}