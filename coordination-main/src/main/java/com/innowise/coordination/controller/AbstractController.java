package com.innowise.coordination.controller;

import com.innowise.coordination.entity.AbstractEntity;
import com.innowise.coordination.service.CommonService;
import com.innowise.coordination.exception.ErrorType;
import com.innowise.coordination.exception.SampleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E>{

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> get(@RequestParam Long id) {
        return service.get(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)
                ));
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<Page<E>> getPage(int page, int size, String order, String column) {
        return ResponseEntity.ok(service.getPage(page, size, order, column));
    }

    @Override
    public ResponseEntity<E> save(@RequestBody E entity) {
        return service.save(entity).map(ResponseEntity::ok)
                .orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_SAVED.getDescription(), entity.toString())
                ));
    }

    @Override
    public ResponseEntity<E> update(@RequestBody E entity) {
        return service.update(entity).map(ResponseEntity::ok)
                .orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_UPDATED.getDescription(), entity)
                ));
    }

    @Override
    public void delete(@RequestParam Long id) {
        E entity = service.get(id)
                .orElseThrow(() -> new SampleException(String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)));
        service.deleteById(id);
    }

    @Override
    public void deleteAll() {
        service.deleteAll();
    }
}
