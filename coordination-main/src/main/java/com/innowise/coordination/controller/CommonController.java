package com.innowise.coordination.controller;

import com.innowise.coordination.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E extends AbstractEntity> {

    @GetMapping
    ResponseEntity<E> get(@RequestParam Long id);

    @GetMapping("/all")
    ResponseEntity<List<E>> getAll();

    @GetMapping("/page")
    ResponseEntity<Page<E>> getPage(@RequestParam int page, @RequestParam int size,
                                    @RequestParam String order, @RequestParam String column);

    @PostMapping
    ResponseEntity<E> save(@RequestBody E entity);

    @PutMapping
    ResponseEntity<E> update(@RequestBody E entity);

    @DeleteMapping
    void delete(@RequestParam Long id);

    @DeleteMapping("/all")
    void deleteAll();

}
