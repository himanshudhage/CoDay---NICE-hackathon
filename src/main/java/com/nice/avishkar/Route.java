package com.nice.avishkar;


public class Route {
    String source;
    String destination;
    String mode;
    String departureTime;
    String arrivalTime;


    public Route(String source, String destination, String mode, String departureTime, String arrivalTime) {
        this.source = source;
        this.destination = destination;
        this.mode = mode;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
