package io.databr.trenssp.models;

import android.graphics.Color;
import android.text.format.DateUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Line {
    private String name, updatedAt;
    private int number;
    private LineStatus lastStatus;
    private LineColor color;

    public Line() {
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

    public String getLastTextStatus() {
        return lastStatus.getMessage();
    }

    public void setLastStatus(LineStatus status) {
        this.lastStatus = status;
    }

    public String getLastUpdatedAt() {
       return DateUtils.getRelativeTimeSpanString(lastStatus.getUpdatedAt().getTime()).toString();
    }

}

