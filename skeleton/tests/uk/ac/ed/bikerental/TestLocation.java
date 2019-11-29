package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TestLocation {
    
    private String address1;
    private String address2;
    private String address3;
    private String postcode1;
    private String postcode2;
    private String postcode3;
    private Location location1;
    private Location location2;
    private Location location3;

    @BeforeEach
    void setUp() throws Exception {
        // TODO: setup some resources before each test
         this.address1 = "13 Traquair Park East";
         this.address2 = "London Luton Airport (LTN), Airport Way, Luton";
         this.address3 = "6 McDonald Road";
         this.postcode1 = "EH12 7AP";
         this.postcode2 = "LU2 9LY";
         this.postcode3 = "EH7 4GT";
         this.location1 = new Location(postcode1,address1);
         this.location2 = new Location(postcode2,address2);
         this.location3 = new Location(postcode3,address3);

    }
    
    // TODO: put some tests here

    @Test
    void isNearToTrue(){
        assertTrue(this.location1.isNearTo(this.location3));
    }

    @Test
    void isNearToFalse(){
        assertFalse(this.location1.isNearTo(this.location2));
    }

}
