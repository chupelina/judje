package com.softuni.model.entity;

import com.softuni.model.entity.enums.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private RoleEnum name;

    public RoleEnum getName() {
        return name;
    }

    public RoleEntity setName(RoleEnum name) {
        this.name = name;
        return this;
    }
}
