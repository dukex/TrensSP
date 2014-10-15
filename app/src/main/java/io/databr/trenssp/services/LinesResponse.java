package io.databr.trenssp.services;

import java.util.Collection;
import io.databr.trenssp.models.Line;

/**
 * Created by duke on 10/15/14.
 */
public class LinesResponse {
    private Collection<Line> lines;

    public LinesResponse() {
    }

    public Collection<Line> getLines() {
        return lines;
    }
}
