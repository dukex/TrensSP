package io.databr.trenssp.models;

import android.graphics.Color;

import org.json.JSONObject;

import java.util.ArrayList;

public class Line {
    private String name, updatedAt, id;
    private int number;
    private LineStatus lastStatus;
    private LineColor color;

    public Line() {
    }

    public Line(String id, String name, String updatedAt, LineStatus status, int number) {

        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.lastStatus = status;
        this.number = number;

    }

    public String getName() { return name;  }
    public void setName(String name) { this.name = name; }

    public String getNumber() { return Integer.toString(number); }
    public void setNumber(int number) { this.number = number; }

    public void setColor(LineColor color) {
        this.color = color;
    }

    public int getHexColor() {
        return Color.parseColor(color.getHex());
    }
}

