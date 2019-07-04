package com.innowise.coordination.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectPositionEmployee is a Querydsl query type for ProjectPositionEmployee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProjectPositionEmployee extends EntityPathBase<ProjectPositionEmployee> {

    private static final long serialVersionUID = 1949040801L;

    public static final QProjectPositionEmployee projectPositionEmployee = new QProjectPositionEmployee("projectPositionEmployee");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath position = createString("position");

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QProjectPositionEmployee(String variable) {
        super(ProjectPositionEmployee.class, forVariable(variable));
    }

    public QProjectPositionEmployee(Path<? extends ProjectPositionEmployee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectPositionEmployee(PathMetadata metadata) {
        super(ProjectPositionEmployee.class, metadata);
    }

}

