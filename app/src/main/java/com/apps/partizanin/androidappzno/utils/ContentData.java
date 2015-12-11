package com.apps.partizanin.androidappzno.utils;

import java.util.ArrayList;
import java.util.List;

public class ContentData {

    private String question = "";
    private int taskCount = 0;
    private int paragraphCount = 0;
    private List<String> answers;

    public ContentData() {
        answers = new ArrayList<>();
    }

    public ContentData(String question, List<String> answers, int taskCount, int paragraphCount) {
        this.paragraphCount = paragraphCount;
        this.question = question;
        this.answers = answers;
        this.taskCount = taskCount;
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

    public int getTaskCount() {
        return taskCount;
    }

    public int getParagraphCount() {
        return paragraphCount;
    }
}
