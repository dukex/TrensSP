package io.databr.trenssp.models;

import java.io.Serializable;

public class Line implements Serializable {
    private String name, updatedAt;
    private int number;
    private LineStatus status;
    private LineColor color;
    private String id;

    public String getName() { return name;  }

    public String getNumber() { return Integer.toString(number); }

    public LineStatus getStatus() {
        return status;
    }

    public LineColor getColor() {
        return color;
    }

    public String getId() {
        return id;
    }
}
