package com.apps.partizanin.androidappzno.utils;


public class ClientData {
    private String paragraph;
    private String task;

    public ClientData() {
    }

    public ClientData(String resource) {
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
