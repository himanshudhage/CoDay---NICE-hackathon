package com.nice.avishkar;

import java.io.IOException;
import java.util.Map;

public interface ITravelOptimizer {
    Map<String, OptimalTravelSchedule> getOptimalTravelOptions(ResourceInfo resourceInfo) throws IOException;
}
