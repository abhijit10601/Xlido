package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Question;

public class QuestionRepository implements IQuestionRepository {
    private final Map<Long, Question> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Question create(Question entity) {
        Question question = new Question(idCounter.incrementAndGet(), entity);
        storage.putIfAbsent(question.getId(), question);
        return question;
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    } 

    @Override
    public List<Question> findByEventId(Long eventId){
        return storage.values().stream()
            .filter(question -> eventId.equals(question.getEvent().getId()))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
