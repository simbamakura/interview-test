package com.security.auth.server.dto;



import com.security.auth.server.domain.Role;
import com.sim.annotations.TargetMapClass;
import com.sim.dto.BaseDto;

import java.util.List;

@TargetMapClass(Role.class)
public class RoleDto extends BaseDto {
    private String name;

    private List<PermissionDto> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
