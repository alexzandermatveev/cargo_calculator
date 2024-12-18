package ru.fastdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;
import ru.fastdelivery.domain.delivery.place.PlaceFactory;
import ru.fastdelivery.properties.provider.CoordinatesLimitProperties;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.WeightPriceProvider;

/**
 * Определение реализаций бинов для всех модулей приложения
 */
@Configuration
public class Beans {
    @Bean
    public PlaceFactory placeConstraints(CoordinatesLimitProperties coordinatesLimitProperties){
        return new PlaceFactory(coordinatesLimitProperties);
    }
    @Bean
    public CurrencyFactory currencyFactory(CurrencyPropertiesProvider currencyProperties) {
        return new CurrencyFactory(currencyProperties);
    }
    @Bean
    public TariffCalculateUseCase tariffCalculateUseCase(WeightPriceProvider weightPriceProvider) {
        return new TariffCalculateUseCase(weightPriceProvider);
    }
}
