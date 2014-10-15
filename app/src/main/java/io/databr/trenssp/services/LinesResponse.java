package io.databr.trenssp.services;

import java.util.List;

import io.databr.trenssp.models.Line;

/**
 * Created by duke on 10/15/14.
 */
public class LinesResponse {
    private List<Line> lines;

    public LinesResponse() {
    }

    public List<Line> getLines() {
        return lines;
    }
}
