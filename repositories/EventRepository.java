package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;

public class EventRepository implements IEventRepository {
    private final Map<Long, Event> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Event create(Event entity) {
        Event event = new Event(idCounter.incrementAndGet(),entity);
        storage.putIfAbsent(event.getId(), event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    } 

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
