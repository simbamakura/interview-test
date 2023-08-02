package com.security.auth.server.config.handler;

import com.security.auth.server.config.data.DataConfig;
import com.security.auth.server.domain.Role;
import com.security.auth.server.handler.role.RolePersistantHandler;
import com.security.auth.server.handler.role.RoleQueryHandler;
import com.security.auth.server.repo.PermissionRepo;
import com.security.auth.server.repo.RoleRepository;
import com.sim.config.validation.ValidationConfig;
import com.sim.handler.api.Handler;
import com.sim.validator.api.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({DataConfig.class, ValidationConfig.class})
public class RoleHandlerConfig {

    @Bean
    public Handler<Role> rolePersistantHandler(RoleRepository roleRepository,
                                               PermissionRepo permissionRepo,
                                               Validator validator) {
        return new RolePersistantHandler(roleRepository, permissionRepo, validator);
    }

    @Bean
    public Handler<Role> roleQueryHandler(RoleRepository roleRepository, Validator validator) {
        return new RoleQueryHandler(roleRepository, validator);
    }

}
