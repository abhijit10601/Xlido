package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {
    Question create(Question question);
    List<Question> findAll();
    Optional<Question> findById(Long id);
    List<Question> findByEventId(Long eventId);
    void deleteById(Long id);
}
