package com.nice.avishkar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TravelOptimizerImpl implements ITravelOptimizer {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public Map<String, OptimalTravelSchedule> getOptimalTravelOptions(ResourceInfo resourceInfo) throws IOException {
        List<RouteWithCost> allRoutes = parseSchedules(resourceInfo.getTransportSchedulePath());
        List<CustomerRequest> requests = parseCustomerRequests(resourceInfo.getCustomerRequestPath());

        Map<String, OptimalTravelSchedule> result = new HashMap<>();

        for (CustomerRequest request : requests) {
            OptimalTravelSchedule schedule = findOptimalRoute(allRoutes, request);
            result.put(request.requestId, schedule);
        }

        return result;
    }

    private OptimalTravelSchedule findOptimalRoute(List<RouteWithCost> routes, CustomerRequest request) {
        Map<String, List<RouteWithCost>> graph = new HashMap<>();
        for (RouteWithCost route : routes) {
            graph.computeIfAbsent(route.source, k -> new ArrayList<>()).add(route);
        }

        Comparator<PathNode> comparator;
        switch (request.criteria.toLowerCase()) {
            case "time":
                comparator = Comparator.comparingInt((PathNode n) -> n.totalTime)
                        .thenComparingInt(n -> n.totalCost)
                        .thenComparingInt(n -> n.hops);
                break;
            case "cost":
                comparator = Comparator.comparingInt((PathNode n) -> n.totalCost)
                        .thenComparingInt(n -> n.totalTime)
                        .thenComparingInt(n -> n.hops);
                break;
            case "hops":
                comparator = Comparator.comparingInt((PathNode n) -> n.hops)
                        .thenComparingInt(n -> n.totalTime)
                        .thenComparingInt(n -> n.totalCost);
                break;
            default:
                throw new IllegalArgumentException("Invalid criteria: " + request.criteria);
        }

        PriorityQueue<PathNode> queue = new PriorityQueue<>(comparator);
        queue.add(new PathNode(request.source, null, new ArrayList<>(), 0, 0, 0));

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();

            if (current.location.equals(request.destination)) {
                long value;
                switch (request.criteria.toLowerCase()) {
                    case "time": value = current.totalTime; break;
                    case "cost": value = current.totalCost; break;
                    case "hops": value = current.hops; break;
                    default: value = 0;
                }
                return new OptimalTravelSchedule(current.path, request.criteria, value);
            }

            if (visited.contains(current.location)) continue;
            visited.add(current.location);

            List<RouteWithCost> neighbors = graph.getOrDefault(current.location, Collections.emptyList());
            for (RouteWithCost neighbor : neighbors) {
                if (current.lastArrivalTime != null) {
                    LocalTime lastArrival = LocalTime.parse(current.lastArrivalTime, TIME_FORMATTER);
                    LocalTime nextDeparture = LocalTime.parse(neighbor.departureTime, TIME_FORMATTER);
                    if (nextDeparture.isBefore(lastArrival)) continue;
                }

                int travelTime = calculateMinutes(neighbor.departureTime, neighbor.arrivalTime);
                int waitingTime = 0;
                if (current.lastArrivalTime != null) {
                    waitingTime = calculateMinutes(current.lastArrivalTime, neighbor.departureTime);
                }
                int newTotalTime = current.totalTime + travelTime + waitingTime;
                int newTotalCost = current.totalCost + neighbor.cost;
                int newHops = current.hops + 1;

                List<Route> newPath = new ArrayList<>(current.path);
                newPath.add(new Route(neighbor.source, neighbor.destination, neighbor.mode, neighbor.departureTime, neighbor.arrivalTime));

                queue.add(new PathNode(neighbor.destination, neighbor.arrivalTime, newPath, newTotalTime, newTotalCost, newHops));
            }
        }

        return new OptimalTravelSchedule(new ArrayList<>(), request.criteria, 0);
    }

    private int calculateMinutes(String start, String end) {
        LocalTime t1 = LocalTime.parse(start, TIME_FORMATTER);
        LocalTime t2 = LocalTime.parse(end, TIME_FORMATTER);
        return (int) java.time.Duration.between(t1, t2).toMinutes();
    }

    private List<RouteWithCost> parseSchedules(Path schedulePath) throws IOException {
        List<RouteWithCost> routes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(schedulePath.toFile()))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                routes.add(new RouteWithCost(
                        tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5])
                ));
            }
        }
        return routes;
    }

    private List<CustomerRequest> parseCustomerRequests(Path requestPath) throws IOException {
        List<CustomerRequest> requests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(requestPath.toFile()))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                requests.add(new CustomerRequest(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
            }
        }
        return requests;
    }

    // Helper classes
    private static class RouteWithCost extends Route {
        int cost;

        public RouteWithCost(String source, String destination, String mode, String departureTime, String arrivalTime, int cost) {
            super(source, destination, mode, departureTime, arrivalTime);
            this.cost = cost;
        }
    }

    private static class PathNode {
        String location;
        String lastArrivalTime;
        List<Route> path;
        int totalTime;
        int totalCost;
        int hops;

        public PathNode(String location, String lastArrivalTime, List<Route> path, int totalTime, int totalCost, int hops) {
            this.location = location;
            this.lastArrivalTime = lastArrivalTime;
            this.path = path;
            this.totalTime = totalTime;
            this.totalCost = totalCost;
            this.hops = hops;
        }
    }

    private static class CustomerRequest {
        String requestId;
        String customerName;
        String source;
        String destination;
        String criteria;

        public CustomerRequest(String requestId, String customerName, String source, String destination, String criteria) {
            this.requestId = requestId;
            this.customerName = customerName;
            this.source = source;
            this.destination = destination;
            this.criteria = criteria;
        }
    }
}
