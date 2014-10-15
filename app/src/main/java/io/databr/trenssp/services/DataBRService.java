package io.databr.trenssp.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by duke on 10/15/14.
 */

public interface DataBRService {
    @GET("/v1/states/sp/transports/trains/lines")
    void listLines(Callback<LinesResponse> cb);

    @GET("/v1/states/sp/transports/trains/lines/{line}")
    LineResponse getLine(@Path("line") String lineUri);
}