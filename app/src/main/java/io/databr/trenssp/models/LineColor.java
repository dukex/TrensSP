package io.databr.trenssp.models;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by duke on 10/14/14.
 */
public class LineColor implements Serializable {
    private String hex;

    public LineColor() {
    }

    public int getHex() {
        return Color.parseColor(hex);
    }
}
