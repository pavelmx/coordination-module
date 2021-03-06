package com.innowise.coordination.service;

import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CommonService <E>{

    Optional<E> get(Long id);

    List<E> getAll();

    Page<E> getPage(int page, int size, String order, String column);

    Optional<E> save(E entity);

    Optional<E> update(E entity);

    void deleteById(Long id);

    void deleteAll();

}

