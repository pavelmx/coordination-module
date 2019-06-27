package com.innowise.coordination.repository;

import com.innowise.coordination.entity.AbstractEntity;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity, L, Q extends EntityPathBase<E>>
        extends JpaRepository<E, L>, QuerydslPredicateExecutor<E>, QuerydslBinderCustomizer<Q>
{
    @Override
    default public void customize(QuerydslBindings bindings, Q root) {
    }
}
