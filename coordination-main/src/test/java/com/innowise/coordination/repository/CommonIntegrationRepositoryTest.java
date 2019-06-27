package com.innowise.coordination.repository;

import com.innowise.coordination.entity.AbstractEntity;
import com.innowise.coordination.repository.AbstractRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class CommonIntegrationRepositoryTest<E extends AbstractEntity, R extends AbstractRepository<E, L, Q>, L, Q extends EntityPathBase<E>> {

    @Autowired
    private R repository;

    public Optional<E> findById(L id){
        return repository.findById(id);
    }

    public List<E> findAll(){
        return repository.findAll();
    }

    public E save(E entity){
        return repository.save(entity);
    }

    public E update(E entity){
        return repository.save(entity);
    }

    public void delete(L id){
        repository.deleteById(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
