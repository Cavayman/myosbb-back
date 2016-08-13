package com.softserve.osbb.model;

/**
 * Created by Anastasiia Fedorak on 8/13/16.
 */
public class Mail {
    private String to;
    private String subject;
    private String text;

    public Mail() {
    }

    public Mail(String to, String text) {
        this.to = to;
        this.text = text;
    }

    public Mail(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
