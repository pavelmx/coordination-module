package com.innowise.coordination.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAbstractEntity extends EntityPathBase<AbstractEntity<?>> {

    private static final long serialVersionUID = -932375788L;

    public static final QAbstractEntity abstractEntity = new QAbstractEntity("abstractEntity");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updated = createDateTime("updated", java.time.LocalDateTime.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractEntity(String variable) {
        super((Class) AbstractEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractEntity(Path<? extends AbstractEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QAbstractEntity(PathMetadata metadata) {
        super((Class) AbstractEntity.class, metadata);
    }

}

