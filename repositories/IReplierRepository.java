package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Replier;

public interface IReplierRepository {
    Replier add(Replier event);
    List<Replier> findAll();
    Optional<Replier> findById(Long id);
    List<Replier> findByQuestionId(Long questionId);
    void deleteById(Long id);
}
