package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Replier;

public class ReplierRepository implements IReplierRepository {
    private final Map<Long, Replier> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Replier add(Replier entity) {
        Replier replier = new Replier(idCounter.incrementAndGet(),entity);
        storage.putIfAbsent(replier.getId(), replier);
        return replier;
    }

    @Override
    public List<Replier> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Replier> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    } 

    @Override
    public List<Replier> findByQuestionId(Long questionId){
        return storage.values().stream()
            .filter(replier -> questionId.equals(replier.getQuestion().getId()))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
