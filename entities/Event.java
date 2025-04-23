package com.crio.xlido.entities;

public class Event {
    private final Long id;
    private final String name;
    private final User user;

    public Event(String name, User user) {
        this.id = null;
        this.name = name;
        this.user = user;
    }

    public Event(Long id, Event other) {
        this.id = id;
        this.name = other.name;
        this.user = other.user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", user=" + user + "]";
    }

}
