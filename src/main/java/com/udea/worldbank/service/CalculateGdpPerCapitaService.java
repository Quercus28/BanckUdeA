package com.udea.worldbank.service;

import com.udea.worldbank.client.ObservationsWorldBankClient;
import com.udea.worldbank.dto.GdpPerCapitaResponse;
import com.udea.worldbank.exception.DataNotFoundException;
import com.udea.worldbank.exception.WorldBankApiException;
import com.udea.worldbank.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public final class CalculateGdpPerCapitaService implements CalculateGdpPerCapitaUseCase {

    private static final Logger log = LoggerFactory.getLogger(CalculateGdpPerCapitaService.class);

    private static final String GDP_INDICATOR = "NY.GDP.MKTP.CD";
    private static final String POPULATION_INDICATOR = "SP.POP.TOTL";
    private static final int USD_SCALE = 2;

    private final ObservationsWorldBankClient observationsClient;

    public CalculateGdpPerCapitaService(ObservationsWorldBankClient observationsClient) {
        this.observationsClient = observationsClient;
    }

    @Override
    public GdpPerCapitaResponse calculateGdpPerCapita(String countryIso3, int year) {
        Double gdp = pointValue(countryIso3, GDP_INDICATOR, year);
        Double population = pointValue(countryIso3, POPULATION_INDICATOR, year);

        if (gdp == null || population == null || population == 0) {
        //if (gdp == null || population == null || population > 0) {
            throw new DataNotFoundException(
                    "Missing GDP or population data for '%s' in %d".formatted(countryIso3, year));
        }

        long populationAmount = population.longValue();
        BigDecimal gdpAmount = BigDecimal.valueOf(gdp).setScale(USD_SCALE, RoundingMode.HALF_UP);
        BigDecimal perCapitaAmount = BigDecimal.valueOf(gdp)
                .divide(BigDecimal.valueOf(populationAmount), USD_SCALE, RoundingMode.HALF_UP);

        return new GdpPerCapitaResponse(
                countryIso3.toUpperCase(),
                year,
                gdpAmount,
                formatUsd(gdpAmount),
                populationAmount,
                perCapitaAmount,
                formatUsd(perCapitaAmount));
    }

    private Double pointValue(String countryIso3, String indicator, int year) {
        try {
            return observationsClient.getObservations(countryIso3, indicator, year, year).stream()
                    .filter(o -> o.value() != null)
                    .map(Observation::value)
                    .findFirst()
                    .orElse(null);
        } catch (WorldBankApiException e) {
            log.error("Could not fetch '{}' for '{}' in {}: {}",
                    indicator, countryIso3, year, e.getMessage());
            throw e;
        }
    }


    private String formatUsd(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }
}
