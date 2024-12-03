package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;


    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var allVolumes = shipment.calcVolumeAllPackages().volume();
        var minimalPrice = weightPriceProvider.minimalPrice();

        var totalCost = weightPriceProvider.costPerCubMeter().multiply(allVolumes)
                .max(weightPriceProvider.costPerKg().multiply(weightAllPackagesKg));

        return totalCost.max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }

    public Price calcWithDistance(Shipment shipment, double distanceInMeters) {
        int eachKilometersWhenRaisePrice = 450;
        double distanceInKilometers = distanceInMeters / 1000;
        Price regularPrice = calc(shipment);

        if (distanceInKilometers > eachKilometersWhenRaisePrice) {
            return regularPrice.multiply(new BigDecimal(distanceInKilometers / eachKilometersWhenRaisePrice));
        } else {
            return regularPrice;
        }
    }
}
