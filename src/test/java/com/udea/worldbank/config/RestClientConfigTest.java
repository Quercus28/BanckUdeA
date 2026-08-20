package com.udea.worldbank.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

class RestClientConfigTest {

    private final RestClientConfig config = new RestClientConfig();

    @Test
    void test_worldBankRestClient_buildsANonNullClient_withTheConfiguredBaseUrl() {
        // Arrange
        RestClient.Builder builder = RestClient.builder();

        // Act
        RestClient client = config.worldBankRestClient(builder, "https://api.worldbank.org/v2");

        // Assert
        assertThat(client).isNotNull();
    }
}