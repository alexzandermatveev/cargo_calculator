package ru.fastdelivery.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.fastdelivery.ControllerTest;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.departure.Departure;
import ru.fastdelivery.domain.delivery.destination.Destination;
import ru.fastdelivery.domain.delivery.place.PlaceFactory;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.request.CargoPackage;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.TariffCalculateUseCase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

class CalculateControllerTest extends ControllerTest {

    final String baseCalculateApi = "/api/v1/calculate";
    @MockBean
    TariffCalculateUseCase useCase;
    @MockBean
    CurrencyFactory currencyFactory;
    @MockBean
    PlaceFactory placeFactory;

    @BeforeEach
    void makeTestPlaceFactory() {
        when(placeFactory.createDeparture(55.446008, 65.339151)).thenReturn(new Departure(55.446008, 65.339151));
        when(placeFactory.createDestination(62.398660, 55.027532)).thenReturn(new Destination(62.398660, 55.027532));
    }


    @Test
    @DisplayName("Валидные данные для расчета стоимость -> Ответ 200")
    void whenValidInputData_thenReturn200() {

        var request = new CalculatePackagesRequest(
                List.of(new CargoPackage(BigInteger.TEN, BigInteger.TEN, BigInteger.TEN, BigInteger.TEN)),
                "RUB",
                placeFactory.createDestination(62.398660, 55.027532),
                placeFactory.createDeparture(55.446008, 65.339151));
        var rub = new CurrencyFactory(code -> true).create("RUB");
        when(useCase.calc(any())).thenReturn(new Price(BigDecimal.valueOf(10), rub));
        when(useCase.calcWithDistance(any(), anyDouble())).thenReturn(new Price(BigDecimal.valueOf(15), rub));
        when(useCase.minimalPrice()).thenReturn(new Price(BigDecimal.valueOf(5), rub));

        ResponseEntity<CalculatePackagesResponse> response =
                restTemplate.postForEntity(baseCalculateApi, request, CalculatePackagesResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Список упаковок == null -> Ответ 400")
    void whenEmptyListPackages_thenReturn400() {
        var request = new CalculatePackagesRequest(null, "RUB", placeFactory.createDestination(62.398660, 55.027532),
                placeFactory.createDeparture(55.446008, 65.339151));

        ResponseEntity<String> response = restTemplate.postForEntity(baseCalculateApi, request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
