package com.crio.xlido.entities;

public class Replier {
    private final Long id;
    private final String reply;
    private User user;
    private Question question;

    public Replier(User user, Question question, String reply) {
        this.id = null;
        this.user = user;
        this.question = question;
        this.reply = reply;
    }

    public Replier(Long id, Replier other){
        this.id = id;
        this.user = other.user;
        this.question = other.question;
        this.reply = other.reply;
    }

    public Long getId() {
        return id;
    }

    public String getReply() {
        return reply;
    }

    public User getUser() {
        return user;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Replier [id=" + id + ", question=" + question.getId() + ", reply=" + reply + ", user="
                + user.getId() + "]";
    }

    
}
