package com.apps.partizanin.androidappzno.utils;

import android.content.res.Resources;
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

public class ContentDataCount  extends AppCompatActivity{

    private int paragraphCount;

    private String resource;

    public ContentDataCount(Resources resources) {
        this.resource = getContentDataCountResource(resources);
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

    private String getContentDataCountResource(Resources resources){
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = resources.openRawResource(R.raw.datacount)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
