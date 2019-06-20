package com.innowise.coordination.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface BaseEntity<L> extends Serializable {

    L getId();

    LocalDateTime getCreated();

    LocalDateTime getUpdated();
}
