package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIPServiceTests {


    @Test
    public void testMyIP (){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.244.183.5");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIP (){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.244.183.ggg");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

}
