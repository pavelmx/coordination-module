package com.innowise.coordination.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectPosition is a Querydsl query type for ProjectPosition
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProjectPosition extends EntityPathBase<ProjectPosition> {

    private static final long serialVersionUID = -343983917L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectPosition projectPosition = new QProjectPosition("projectPosition");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> plannedEndDate = createDate("plannedEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> plannedStartDate = createDate("plannedStartDate", java.time.LocalDate.class);

    public final QProject project;

    public final QProjectPositionEmployee projectPositionEmployee;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QProjectPosition(String variable) {
        this(ProjectPosition.class, forVariable(variable), INITS);
    }

    public QProjectPosition(Path<? extends ProjectPosition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectPosition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectPosition(PathMetadata metadata, PathInits inits) {
        this(ProjectPosition.class, metadata, inits);
    }

    public QProjectPosition(Class<? extends ProjectPosition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.projectPositionEmployee = inits.isInitialized("projectPositionEmployee") ? new QProjectPositionEmployee(forProperty("projectPositionEmployee")) : null;
    }

}

