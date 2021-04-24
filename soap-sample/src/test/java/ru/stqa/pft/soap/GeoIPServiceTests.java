package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

  @Test

  public void testGeoIPService() {

    String isoCountryName = new GeoIPService().getGeoIPServiceSoap12().getCountryISO2ByName("USA");
    assertEquals(isoCountryName, "<GeoIP><Country>US</Country></GeoIP>");


  }
}
