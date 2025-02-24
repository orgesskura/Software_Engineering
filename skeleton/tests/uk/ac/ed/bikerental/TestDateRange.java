package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateRange {
    private DateRange dateRange1, dateRange2, dateRange3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        this.dateRange1 = new DateRange(LocalDate.of(2019, 1, 7),
                LocalDate.of(2019, 1, 10));
        this.dateRange2 = new DateRange(LocalDate.of(2019, 1, 5),
                LocalDate.of(2019, 1, 23));
        this.dateRange3 = new DateRange(LocalDate.of(2015, 1, 7),
                LocalDate.of(2018, 1, 10));
    }

    // Sample JUnit tests checking toYears works
    @Test
    void testToYears1() {
        assertEquals(0, this.dateRange1.toYears());
    }

    @Test
    void testToYears3() {
        assertEquals(3, this.dateRange3.toYears());
    }

       @Test
     void testOverlapsTrue() {
         // TODO: check we can see when two date ranges overlap
         LocalDate date1 = LocalDate.of(2019,Month.SEPTEMBER,21);
         LocalDate date2 = LocalDate.of(2010,Month.MARCH,2);
         DateRange dates1 = new DateRange(date2,date1);
         assertTrue(dates1.overlaps(this.dateRange3));

        }

     @Test
     void testOverlapsFalse() {
        // TODO: check we can see when two date ranges  don't overlap
        LocalDate date1 = LocalDate.of(2011,Month.SEPTEMBER,21);
        LocalDate date2 = LocalDate.of(2010,Month.MARCH,2);
        DateRange dates1 = new DateRange(date2,date1);
        assertFalse(dates1.overlaps(this.dateRange3));
     }

    // TODO: put some of your own unit tests here
    @Test
    void testOverlapsCompletelyTrue(){
        LocalDate date1 = LocalDate.of(2017,Month.SEPTEMBER,21);
        LocalDate date2 = LocalDate.of(2016,Month.MARCH,2);
        DateRange dates1 = new DateRange(date2,date1);
        assertTrue(dates1.overlapsCompletely(dates1)); 
    }

    @Test
    void testOverlapsCompletelyFalse(){
        LocalDate date1 = LocalDate.of(2020,Month.SEPTEMBER,21);
        LocalDate date2 = LocalDate.of(2016,Month.MARCH,2);
        DateRange dates1 = new DateRange(date2,date1);
        assertTrue(dates1.overlapsCompletely(dates1));
    }
}
