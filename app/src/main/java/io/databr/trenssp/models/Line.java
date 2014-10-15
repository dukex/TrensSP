package io.databr.trenssp.models;

import java.util.ArrayList;

public class Line {
    private String name, updatedAt, id;
    private int number;
    private LineStatus lastStatus;

    public Line() {
    }

    public Line(String id, String name, String updatedAt, LineStatus status, int number) {

        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.lastStatus = status;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

