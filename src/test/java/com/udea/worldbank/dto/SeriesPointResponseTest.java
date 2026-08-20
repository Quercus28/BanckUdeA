package com.udea.worldbank.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeriesPointResponseTest {

    @Test
    void test_accessors_returnValuesUsedInConstruction() {
        // Arrange & Act
        SeriesPointResponse point = new SeriesPointResponse(2020, 212559409.0);

        // Assert
        assertThat(point.year()).isEqualTo(2020);
        assertThat(point.value()).isEqualTo(212559409.0);
    }
}