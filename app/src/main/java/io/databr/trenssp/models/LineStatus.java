package io.databr.trenssp.models;

import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by duke on 10/14/14.
*/

public class LineStatus implements Serializable {
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("created_at") private String createdAt;


    private String message;
    private String createdAtAgo;

    public String getMessage() {
        return message;
    }

    public String getUpdatedAgo() throws ParseException {
        return ago(updatedAt);
    }

    public String getCreatedAtAgo() throws ParseException {
        return ago(createdAt);
    }

    private String ago(String d) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        format.setTimeZone(TimeZone.getTimeZone("America/SaoPaulo"));
        Date date = format.parse(d);

        return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();

    }

}