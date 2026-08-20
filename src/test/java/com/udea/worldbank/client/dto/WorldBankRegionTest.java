package com.udea.worldbank.client.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorldBankRegionTest {

    @Test
    void test_accessors_returnValuesUsedInConstruction() {
        // Arrange & Act
        WorldBankRegion region = new WorldBankRegion("LCN", "ZJ", "Latin America & Caribbean");

        // Assert
        assertThat(region.id()).isEqualTo("LCN");
        assertThat(region.iso2Code()).isEqualTo("ZJ");
        assertThat(region.value()).isEqualTo("Latin America & Caribbean");
    }
}