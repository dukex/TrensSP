package io.databr.trenssp.models;

import android.graphics.Color;

/**
 * Created by duke on 10/14/14.
 */
public class LineColor {
    private String hex;

    public LineColor() {
    }

    public int getHex() {
        return Color.parseColor(hex);
    }
}
