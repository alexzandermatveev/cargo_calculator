package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var allVolumes = shipment.calcVolumeAllPackages().volume();
        var minimalPrice = weightPriceProvider.minimalPrice();

        var totalCost = weightPriceProvider.costPerMeter().multiply(allVolumes)
                .max(weightPriceProvider.costPerKg().multiply(weightAllPackagesKg));

        return totalCost.max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
