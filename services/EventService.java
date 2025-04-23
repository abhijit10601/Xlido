package com.crio.xlido.services;

import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IUserRepository;

public class EventService {
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;


    public EventService (IEventRepository eventRepository, IUserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(String name, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));

        Event newEvent = new Event(name, user);

        Event event = eventRepository.create(newEvent);

        return event;
    }

    public void deleteEvent(Long eventId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with an id " + userId + " does not exist"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event with an id " + eventId + " does not exist"));

        if(event.getUser().getId() != user.getId()){
            throw new RuntimeException("User with an id " + userId + " is not a organizer of Event with an id " + eventId);
        }

        eventRepository.deleteById(eventId);
    }
}
