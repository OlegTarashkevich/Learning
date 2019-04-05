package com.prilaga.news.util;

import com.prilaga.data.utils.DateUtil;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Oleg Tarashkevich on 05/04/2019.
 */
public class DateJsonAdapter extends JsonAdapter<Date> {

    @Override
    public synchronized Date fromJson(JsonReader reader) throws IOException {
        final String string = reader.nextString();

        try {
            return DateUtil.dateFormatLong().parse(string);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

    @Override
    public synchronized void toJson(JsonWriter writer, Date value) throws IOException {
        final String string = DateUtil.dateFormatLong().format(value);
        writer.value(string);
    }
}