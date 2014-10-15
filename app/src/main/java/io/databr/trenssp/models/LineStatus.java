package io.databr.trenssp.models;

import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by duke on 10/14/14.
*/

public class LineStatus {
    @SerializedName("updated_at") private String updatedAt;

    private String message;

    public String getMessage() {
        return message;
    }

    public String getUpdatedAgo() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        format.setTimeZone(TimeZone.getTimeZone("America/SaoPaulo"));
        Date updatedAtDate = format.parse(updatedAt);

        return DateUtils.getRelativeTimeSpanString(updatedAtDate.getTime()).toString();
    }
}