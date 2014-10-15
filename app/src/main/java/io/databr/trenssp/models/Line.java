package io.databr.trenssp.models;

public class Line {
    private String name, updatedAt;
    private int number;
    private LineStatus status;
    private LineColor color;

    public String getName() { return name;  }
    public String getNumber() { return Integer.toString(number); }

    public LineStatus getStatus() {
        return status;
    }

    public LineColor getColor() {
        return color;
    }
}
