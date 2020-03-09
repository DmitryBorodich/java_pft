package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.131.196.4");
        System.out.println(ipLocation);
    }
}
