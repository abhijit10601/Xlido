package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;

public interface IEventRepository {
    Event create(Event event);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    void deleteById(Long id);
}
