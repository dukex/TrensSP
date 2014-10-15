package io.databr.trenssp.models;

/**
 * Created by duke on 10/14/14.
 */
public class LineColor {
    private String hex;

    public LineColor() {
    }

    public LineColor(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
