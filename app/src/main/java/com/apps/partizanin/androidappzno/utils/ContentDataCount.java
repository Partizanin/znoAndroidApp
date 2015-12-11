package com.apps.partizanin.androidappzno.utils;

import android.support.v7.app.AppCompatActivity;

import com.apps.partizanin.androidappzno.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class ContentDataCount  {

    private int paragraphCount;

    private String resource;

    public ContentDataCount(String resource) {
        this.resource = resource;
        paragraphCount = getParagraphsCount();
    }

    public int getParagraphCount() {
        return paragraphCount;
    }

    public int getTaskCount(String paragraph) {
        int result = 0;
        try {
            JSONObject paragraphs = new JSONObject(resource).getJSONObject("paragraphs");
            JSONObject paragraphOgj = paragraphs.getJSONObject(paragraph);

            result = paragraphOgj.getInt("tasks");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }

    private int getParagraphsCount() {
        int result = 0;

        try {
            JSONObject paragraphs  = new JSONObject(resource).getJSONObject("paragraphs");
            result = paragraphs.getInt("cont");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
