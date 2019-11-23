package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.Month;
class LinearDepreciationPolicyTest {
    private Bike bike1,bike2;
    private BikeType type1,type2;
    private LocalDate date1,date2,date3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup resources before each test
        BigDecimal a = new BigDecimal(120);
        BigDecimal a1 = new BigDecimal(0.1);
        BigDecimal b = new BigDecimal(90);
        BigDecimal b1 = new BigDecimal(0.1);
        this.type1 = new BikeType("road",a,a1);
        this.type2 = new BikeType("mountain",b,b1);
        LocalDate date1 = LocalDate.of(2014,Month.MARCH,25);
        LocalDate date2 = LocalDate.of(2010,Month.SEPTEMBER,15);
        LocalDate date3 = LocalDate.now();
        this.bike1 = new Bike(date1,type1);
        this.bike2 = new Bike(date2,type2);
    }

    @Test
    void calculateValue1(){
        LinearDepreciationPolicy pol = new LinearDepreciationPolicy();
        BigDecimal d = pol.calculateValue(bike1, date3);
        double a =(double) d.doubleValue();
        assertEquals(30.0,a);
    }
}