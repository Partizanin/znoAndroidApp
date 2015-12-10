package com.apps.partizanin.androidappzno.utils;

/**
 * Created by Partizanin on 10.12.2015.
 */
public class ClientData {
    private String paragraph;
    private String task;

    public ClientData() {
    }

    public ClientData(String paragraph, String task) {
        this.paragraph = paragraph;
        this.task = task;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getTask() {
        return task;
    }
}
