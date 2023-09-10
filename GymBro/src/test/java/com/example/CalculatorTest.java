package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void oneRepMaxOutsideBounds() {
        // Lower bound
        assertThrows(IllegalArgumentException.class, () -> {
            Calculator.oneRepMax(225, 0);
        });
        // Upper bound
        assertThrows(IllegalArgumentException.class, () -> {
            Calculator.oneRepMax(225, 11);
        });
    }

    @Test
    public void oneRepMaxCalculationCorrect() {
        // Min reps
        assertEquals(225.0f, Calculator.oneRepMax(225, 1), 0);
        // Middle reps
        assertEquals(253.0f, Calculator.oneRepMax(225, 5), 0);
        // Max reps
        assertEquals(300.0f, Calculator.oneRepMax(225, 10), 0);
        // Different weight
        assertEquals(112.0f, Calculator.oneRepMax(100, 5), 0);
    }

    @Test
    public void goalProgressionTimelineCorrect() {
        // Conservative
        assertEquals(
            "[2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.0]",
            Calculator.goalProgressionTimeline(225, 1, 225f+17.0f, "conservative").toString()
        );
        // Aggressive
        assertEquals(
            "[5.0, 5.0, 5.0, 5.0, 5.0, 2.5, 1.0]",
            Calculator.goalProgressionTimeline(225, 1, 225f+28.5f, "aggressive").toString()
        );
    }
}
