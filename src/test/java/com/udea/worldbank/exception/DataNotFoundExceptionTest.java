package com.udea.worldbank.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataNotFoundExceptionTest {

    @Test
    void test_constructor_setsTheMessage() {
        // Arrange & Act
        DataNotFoundException ex = new DataNotFoundException("no data found");

        // Assert
        assertThat(ex.getMessage()).isEqualTo("no data found");
    }
}
