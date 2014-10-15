package io.databr.trenssp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static java.util.TimeZone.*;

/**
 * Created by duke on 10/14/14.
 */
public class LineStatus {
    private String message;
    private Date updatedAt;


    public LineStatus(String message, String updatedAt) throws ParseException {
        this.message = message;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        format.setTimeZone(TimeZone.getTimeZone("America/SaoPaulo"));
        this.updatedAt = format.parse(updatedAt);
    }

    public String getMessage() {
        return message;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }
}
