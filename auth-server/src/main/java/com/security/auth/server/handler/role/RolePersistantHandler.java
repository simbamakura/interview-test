package com.security.auth.server.handler.role;

import com.security.auth.server.domain.Role;
import com.security.auth.server.repo.PermissionRepo;
import com.sim.data.repo.api.BaseRepo;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractDefaultHandler;
import com.sim.validator.api.Validator;

public class RolePersistantHandler extends AbstractDefaultHandler<Role> {

    public static final String USE_CASE = "role-persistance";
    private final PermissionRepo permissionRepo;

    public RolePersistantHandler(BaseRepo<Role> baseRepo,
                                 PermissionRepo permissionRepo, Validator validator) {
        super(baseRepo, validator);
        this.permissionRepo = permissionRepo;
    }


    @Override
    protected Role execute(HandlerContext context) {
        Role role = this.getBaseEntity(context);
        role = this.removeNullProperties(role);
        return this.baseRepo.save(role);
    }
}
