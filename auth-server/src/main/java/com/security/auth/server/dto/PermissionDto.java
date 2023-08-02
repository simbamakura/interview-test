package com.security.auth.server.dto;


import com.security.auth.server.domain.Permission;
import com.sim.annotations.TargetMapClass;
import com.sim.dto.BaseDto;

@TargetMapClass(Permission.class)
public class PermissionDto extends BaseDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
