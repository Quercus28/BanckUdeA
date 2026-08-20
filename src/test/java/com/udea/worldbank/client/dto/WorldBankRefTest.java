package com.udea.worldbank.client.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorldBankRefTest {

    @Test
    void test_accessors_returnValuesUsedInConstruction() {
        // Arrange & Act
        WorldBankRef ref = new WorldBankRef("BR", "Brazil");

        // Assert
        assertThat(ref.id()).isEqualTo("BR");
        assertThat(ref.value()).isEqualTo("Brazil");
    }
}