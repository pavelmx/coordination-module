package com.innowise.coordination.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = -1218205533L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created = _super.created;

    public final StringPath descriptionTask = createString("descriptionTask");

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final NumberPath<Float> hoursForTask = createNumber("hoursForTask", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public final DatePath<java.time.LocalDate> reportDate = createDate("reportDate", java.time.LocalDate.class);

    public final EnumPath<ReportType> reportType = createEnum("reportType", ReportType.class);

    public final StringPath task = createString("task");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated = _super.updated;

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

