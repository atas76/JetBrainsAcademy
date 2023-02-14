package projects.cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevenueCalculatorTest {

    @Test
    void testCalculateSmallRoomRevenue() {
        RevenueCalculator revenueCalculator = new RevenueCalculator(4, 5);

        assertEquals(200, revenueCalculator.doCalculate());
    }

    @Test
    void testCalculateLargeRoomRevenueEvenRows() {
        RevenueCalculator revenueCalculator = new RevenueCalculator(8, 9);

        assertEquals(648, revenueCalculator.doCalculate());
    }

    @Test
    void testCalculateLargeRoomRevenueOddRows() {
        RevenueCalculator revenueCalculator = new RevenueCalculator(9, 7);

        assertEquals(560, revenueCalculator.doCalculate());
    }
}
