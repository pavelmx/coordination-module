package com.innowise.coordination.service;

import java.util.List;
import java.util.Optional;

public interface CommonService <E>{

    Optional<E> get(Long id);

    List<E> getAll();

    Optional<E> save(E entity);

    Optional<E> update(E entity);

    void deleteById(Long id);

    void deleteAll();

}

