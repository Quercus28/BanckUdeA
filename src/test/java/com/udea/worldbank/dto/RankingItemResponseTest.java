package com.udea.worldbank.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankingItemResponseTest {

    @Test
    void test_accessors_returnValuesUsedInConstruction() {
        // Arrange & Act
        RankingItemResponse item = new RankingItemResponse(1, "USA", "United States", 25_000_000_000_000.0);

        // Assert
        assertThat(item.position()).isEqualTo(1);
        assertThat(item.countryIso3()).isEqualTo("USA");
        assertThat(item.countryName()).isEqualTo("United States");
        assertThat(item.value()).isEqualTo(25_000_000_000_000.0);
    }
}