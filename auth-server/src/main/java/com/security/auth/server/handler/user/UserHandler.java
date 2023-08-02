package com.security.auth.server.handler.user;

import com.security.auth.server.domain.Role;
import com.security.auth.server.domain.User;
import com.security.auth.server.repo.RoleRepository;
import com.sim.data.repo.api.BaseRepo;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractDefaultHandler;
import com.sim.validator.api.Validator;

import java.util.Set;

public class UserHandler extends AbstractDefaultHandler<User> {

    public static final String USE_CASE = "user-save-handler";
    private final RoleRepository roleRepository;


    public UserHandler(BaseRepo<User> baseRepo, Validator validator, RoleRepository roleRepository) {
        super(baseRepo, validator);
        this.roleRepository = roleRepository;
    }

    @Override
    protected User execute(HandlerContext context) {
        User user = getBaseEntity(context);
        Set<Role> roles = user.getRoles();
        user = removeNullProperties(user);
        user.getRoles().clear();
        baseRepo.save(user);
        user.getRoles().addAll(roles);
        return baseRepo.save(user);
    }
}
