package com.innowise.coordination.service;

import com.innowise.coordination.entity.AbstractEntity;
import com.innowise.coordination.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends AbstractRepository<E, Long>>
implements  CommonService<E>{

    private final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> getPage(int page, int size, String order, String column){
        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        return repository.findAll(pageable);
    }

    @Override
    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public Optional<E> update(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
