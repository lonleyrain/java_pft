package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

  @Test

  public void testGeoIPService() {

    String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("46.53.246.20");
    assertEquals(ipLocation20, "<GeoIP><Country>BY</Country><State>04</State></GeoIP>");


  }
}
