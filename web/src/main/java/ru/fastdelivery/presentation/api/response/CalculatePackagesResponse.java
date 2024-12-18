package ru.fastdelivery.presentation.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.ScriptAssert;
import ru.fastdelivery.domain.common.price.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CalculatePackagesResponse(
        @Schema(description = "Итоговая цена", example = "300.33")
        BigDecimal totalPrice,
        @Schema(description = "Минимальная цена", example = "30.00")
        BigDecimal minimalPrice,
        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        String currencyCode
) {
    public CalculatePackagesResponse(Price totalPrice, Price minimalPrice) {
        this(totalPrice.amount().setScale(2, RoundingMode.HALF_UP),
                minimalPrice.amount(), totalPrice.currency().getCode());

        if (currencyIsNotEqual(totalPrice, minimalPrice)) {
            throw new IllegalArgumentException("Currency codes must be the same");
        }
    }

    private static boolean currencyIsNotEqual(Price priceLeft, Price priceRight) {
        return !priceLeft.currency().equals(priceRight.currency());
    }

}
