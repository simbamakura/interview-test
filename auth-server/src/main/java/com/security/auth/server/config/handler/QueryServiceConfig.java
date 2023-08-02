package com.security.auth.server.config.handler;

import com.security.auth.server.config.data.DataConfig;
import com.security.auth.server.domain.Permission;
import com.security.auth.server.domain.Role;
import com.security.auth.server.domain.User;
import com.security.auth.server.handler.user.UserQueryHandler;
import com.security.auth.server.repo.PermissionRepo;
import com.security.auth.server.repo.RoleRepository;
import com.security.auth.server.repo.UserRepository;
import com.sim.config.validation.ValidationConfig;
import com.sim.handler.api.Handler;
import com.sim.handler.impl.DefaultQueryByIdHandlerImpl;
import com.sim.validator.api.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataConfig.class, ValidationConfig.class})
public class QueryServiceConfig {

    @Bean
    public Handler<Permission> permissionByIdHandler(final Validator validator, final PermissionRepo permissionRepo) {
        return new DefaultQueryByIdHandlerImpl<>(permissionRepo, validator);
    }

    @Bean
    public Handler<Role> roleByIdHandler(final Validator validator, final RoleRepository roleRepository) {
        return new DefaultQueryByIdHandlerImpl<>(roleRepository, validator);
    }

    @Bean
    public Handler<User> userByIdUserHandler(final Validator validator, final UserRepository userRepository) {
        return new DefaultQueryByIdHandlerImpl<>(userRepository, validator);
    }

    @Bean
    public Handler<User> userQueryHandler(UserRepository userRepository, Validator validator) {
        return new UserQueryHandler(userRepository, validator);
    }
}
