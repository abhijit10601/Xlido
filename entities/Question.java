package com.crio.xlido.entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private final Long id;
    private final String content;
    private final User user;
    private final Event event;
    private List<User> listOfUpvotedUsers;
    private List<Replier> listOfRepliers;
    private Integer totalUpvotes;
    
    public Question(String content, User user, Event event) {
        this.id = null;
        this.content = content;
        this.user = user;
        this.event = event;
        this.totalUpvotes = 0;
        this.listOfUpvotedUsers = new ArrayList<User>();
        this.listOfRepliers = new ArrayList<Replier>();
    }

    public Question(Long id, Question other) {
        this.id = id;
        this.content = other.content;
        this.user = other.user;
        this.event = other.event;
        this.totalUpvotes = 0;
        this.listOfUpvotedUsers = new ArrayList<User>();
        this.listOfRepliers = new ArrayList<Replier>();
    }
    
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public User getUser() {
        return user;
    }
    
    public Event getEvent() {
        return event;
    }

    public Integer getTotalUpvotes() {
        return totalUpvotes;
    }

    public List<User> getListOfUpvotedUsers() {
        return listOfUpvotedUsers;
    }
    
    public List<Replier> getListOfRepliers() {
        return listOfRepliers;
    }
    
    public void upvoteByUser(User user){
        this.listOfUpvotedUsers.add(user);

        this.totalUpvotes++;
    }


    @Override
    public String toString() {
        return "Question [content=" + content + ", event=" + event + ", id=" + id
                + ", totalUpvotes=" + totalUpvotes + ", user=" + user.getId() + "]";
    }

    
}