package com.apps.partizanin.androidappzno.utils;

import java.util.ArrayList;
import java.util.List;

public class ContentData {

    private String question = "";
    private List<String> answers;

    public ContentData() {
        answers = new ArrayList<>();
    }

    public ContentData(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
