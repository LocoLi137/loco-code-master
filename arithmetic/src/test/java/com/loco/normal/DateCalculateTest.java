package com.loco.normal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description:
 */
class DateCalculateTest {
    private DateCalculate calculate;

    public DateCalculateTest() {
        calculate = new DateCalculate();
    }

    @Test
    void numberOfTwoDaysDifferent() {
        LocalDate from = LocalDate.of(2024, 2, 1);
        LocalDate to = LocalDate.of(2024, 3, 1);
        assertEquals(29, calculate.numberOfTwoDaysDifferent(from, to));
    }
}