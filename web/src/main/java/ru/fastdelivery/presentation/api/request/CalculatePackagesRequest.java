package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.fastdelivery.domain.delivery.departure.Departure;
import ru.fastdelivery.domain.delivery.destination.Destination;

import java.util.List;

@Schema(description = "Данные для расчета стоимости доставки")
public record CalculatePackagesRequest(
        @Schema(description = "Список упаковок отправления",
                example = "[{\"weight\": 4056.45, length: 100, width: 250, height: 30}]")
        @NotNull
        @NotEmpty
        @Size(min = 1, message = "как минимум одна упаковка")
        List<CargoPackage> packages,

        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        @NotNull
        String currencyCode,

        @Schema(description = "Точка назначения", example = "{latitude: 73.398660, longitude: 55.027532}")
        @NotNull
        Destination destination,
        @Schema(description = "Точка отправления", example = "{latitude: 55.446008, longitude: 65.339151}")
        @NotNull
        Departure departure
) {
}
