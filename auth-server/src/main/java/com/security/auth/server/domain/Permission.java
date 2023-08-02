package com.security.auth.server.domain;


import com.security.auth.server.dto.PermissionDto;
import com.sim.annotations.TargetMapClass;
import com.sim.data.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@TargetMapClass(PermissionDto.class)
public class Permission extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}