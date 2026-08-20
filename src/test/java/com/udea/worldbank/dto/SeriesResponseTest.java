package com.udea.worldbank.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeriesResponseTest {

    @Test
    void test_accessors_returnValuesUsedInConstruction() {
        // Arrange
        List<SeriesPointResponse> points = List.of(new SeriesPointResponse(2020, 212559409.0));

        // Act
        SeriesResponse series = new SeriesResponse("BRA", "Brazil", "SP.POP.TOTL", "Population, total", points);

        // Assert
        assertThat(series.countryIso3()).isEqualTo("BRA");
        assertThat(series.countryName()).isEqualTo("Brazil");
        assertThat(series.indicatorId()).isEqualTo("SP.POP.TOTL");
        assertThat(series.indicatorName()).isEqualTo("Population, total");
        assertThat(series.points()).isEqualTo(points);
    }
}
