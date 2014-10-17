package io.databr.trenssp.services;

import java.util.List;

import io.databr.trenssp.models.LineStatus;

/**
 * Created by duke on 10/17/14.
 */
public class StatusesResponse {
    private List<LineStatus> statuses;

    public List<LineStatus> getStatuses() {
        return statuses;
    }
}
