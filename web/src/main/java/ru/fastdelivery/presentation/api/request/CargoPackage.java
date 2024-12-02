package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record CargoPackage(
        @Schema(description = "Вес упаковки, граммы", example = "5667.45")
        BigInteger weight,
        @Schema(description = "Длина груза, мм", example = "100")
        BigInteger length,
        @Schema(description = "Ширина груза, мм", example = "250")
        BigInteger width,
        @Schema(description = "Высота груза, мм", example = "30")
        BigInteger height
) {
}
