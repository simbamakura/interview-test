package com.security.auth.server.handler.password;

import com.security.auth.server.domain.Role;
import com.security.auth.server.domain.User;
import com.security.auth.server.repo.RoleRepository;
import com.sim.data.repo.api.BaseRepo;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractDefaultHandler;
import com.sim.validator.api.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class PasswordInitialiserHandler extends AbstractDefaultHandler<User> {

    public static final String USE_CASE = "password-initialise";
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public PasswordInitialiserHandler(BaseRepo<User> baseRepo, PasswordEncoder passwordEncoder, Validator validator, RoleRepository roleRepository) {
        super(baseRepo, validator);
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    protected User execute(HandlerContext context) {
        User user = getBaseEntity(context);
        user = removeNullProperties(user);
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(role));
        String encodedPassword = passwordEncoder.encode("#pass123");
        user.setPassword(encodedPassword);
        return baseRepo.save(user);
    }
}
