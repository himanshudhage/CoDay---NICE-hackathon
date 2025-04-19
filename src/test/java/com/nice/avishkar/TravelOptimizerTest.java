package com.nice.avishkar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TravelOptimizerTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-1/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-1/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals(90, result.get("1").getValue());
        Assert.assertEquals("time", result.get("1").getCriteria().toLowerCase());

        Assert.assertEquals(0, result.get("2").getValue());
        Assert.assertEquals("cost", result.get("2").getCriteria().toLowerCase());
        Assert.assertEquals(0, result.get("2").getRoutes().size());

        Assert.assertEquals(370, result.get("3").getValue());
        Assert.assertEquals("cost", result.get("3").getCriteria().toLowerCase());
    }

    @Test
    public void test2() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-2/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-2/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals(1, result.get("1").getValue());
        Assert.assertEquals("hops", result.get("1").getCriteria().toLowerCase());

        Assert.assertEquals(40, result.get("5").getValue());
        Assert.assertEquals("time", result.get("5").getCriteria().toLowerCase());

        Assert.assertEquals(105, result.get("10").getValue());
        Assert.assertEquals("time", result.get("10").getCriteria().toLowerCase());

        Assert.assertEquals("E6AEDC", result.get("4").getRoutes().get(0).getSource());
        Assert.assertEquals("800878", result.get("4").getRoutes().get(0).getDestination());
        Assert.assertEquals("18:25", result.get("4").getRoutes().get(0).getDepartureTime());
        Assert.assertEquals("19:30", result.get("4").getRoutes().get(0).getArrivalTime());


    }

    @Test
    public void test3() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-3/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-3/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals(270, result.get("24").getValue());
        Assert.assertEquals("cost", result.get("24").getCriteria().toLowerCase());

        Assert.assertEquals(540, result.get("13").getValue());
        Assert.assertEquals("cost", result.get("13").getCriteria().toLowerCase());

        Assert.assertEquals(1, result.get("7").getValue());
        Assert.assertEquals("hops", result.get("7").getCriteria().toLowerCase());

        Assert.assertEquals("CACE5F", result.get("18").getRoutes().get(0).getSource());
        Assert.assertEquals("7B1858", result.get("18").getRoutes().get(0).getDestination());
        Assert.assertEquals("20:30", result.get("18").getRoutes().get(0).getDepartureTime());
        Assert.assertEquals("21:25", result.get("18").getRoutes().get(0).getArrivalTime());

    }

    @Test
    public void test4() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-4/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-4/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals(1, result.get("800").getValue());
        Assert.assertEquals("hops", result.get("800").getCriteria().toLowerCase());

        Assert.assertEquals(50, result.get("626").getValue());
        Assert.assertEquals("cost", result.get("626").getCriteria().toLowerCase());

        Assert.assertEquals(320, result.get("410").getValue());
        Assert.assertEquals("cost", result.get("410").getCriteria().toLowerCase());
    }

    @Test
    public void test5() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-5/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-5/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals(250, result.get("93").getValue());
        Assert.assertEquals("cost", result.get("93").getCriteria().toLowerCase());

        Assert.assertEquals(160, result.get("9357").getValue());
        Assert.assertEquals("cost", result.get("9357").getCriteria().toLowerCase());

        Assert.assertEquals(1, result.get("14962").getValue());
        Assert.assertEquals("hops", result.get("14962").getCriteria().toLowerCase());

        Assert.assertEquals("069782", result.get("9402").getRoutes().get(1).getSource());
        Assert.assertEquals("180AD5", result.get("9402").getRoutes().get(1).getDestination());
        Assert.assertEquals("12:55", result.get("9402").getRoutes().get(1).getDepartureTime());
        Assert.assertEquals("15:45", result.get("9402").getRoutes().get(1).getArrivalTime());

    }

    @Test
    public void test6() throws IOException {

        Path schedulesFilePath = Paths.get("src/main/resources/TestCase-6/Schedules.csv");
        Path customerRequestFilePath = Paths.get("src/main/resources/TestCase-6/CustomerRequests.csv");

        ResourceInfo resourceInfo = new ResourceInfo(schedulesFilePath, customerRequestFilePath);
        TravelOptimizerImpl analyzer = new TravelOptimizerImpl();

        Map<String, OptimalTravelSchedule> result = analyzer.getOptimalTravelOptions(resourceInfo);

        Assert.assertNotNull(result);

        Assert.assertEquals("4E4594", result.get("13400").getRoutes().get(0).getSource());
        Assert.assertEquals("245447", result.get("13400").getRoutes().get(0).getDestination());
        Assert.assertEquals("03:15", result.get("13400").getRoutes().get(0).getDepartureTime());
        Assert.assertEquals("05:00", result.get("13400").getRoutes().get(0).getArrivalTime());
        Assert.assertEquals(105, result.get("13400").getValue());
        Assert.assertEquals("time", result.get("13400").getCriteria().toLowerCase());

        Assert.assertEquals(170, result.get("21344").getValue());
        Assert.assertEquals("cost", result.get("21344").getCriteria().toLowerCase());

        Assert.assertEquals(210, result.get("711").getValue());
        Assert.assertEquals("cost", result.get("711").getCriteria().toLowerCase());

        Assert.assertEquals(1, result.get("24974").getValue());
        Assert.assertEquals("hops", result.get("24974").getCriteria().toLowerCase());

    }
}
