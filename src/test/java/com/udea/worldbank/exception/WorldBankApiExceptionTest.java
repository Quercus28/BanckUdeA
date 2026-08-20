package com.udea.worldbank.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WorldBankApiExceptionTest {

    @Test
    void test_constructor_withMessageOnly_setsTheMessageAndHasNoCause() {
        // Arrange & Act
        WorldBankApiException ex = new WorldBankApiException("external failure");

        // Assert
        assertThat(ex.getMessage()).isEqualTo("external failure");
        assertThat(ex.getCause()).isNull();
    }

    @Test
    void test_constructor_withMessageAndCause_setsBoth() {
        // Arrange
        Throwable cause = new RuntimeException("timeout");

        // Act
        WorldBankApiException ex = new WorldBankApiException("external failure", cause);

        // Assert
        assertThat(ex.getMessage()).isEqualTo("external failure");
        assertThat(ex.getCause()).isSameAs(cause);
    }
}