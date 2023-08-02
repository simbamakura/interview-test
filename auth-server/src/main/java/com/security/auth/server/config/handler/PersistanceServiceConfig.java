package com.security.auth.server.config.handler;

import com.security.auth.server.config.data.DataConfig;
import com.security.auth.server.domain.Permission;
import com.security.auth.server.domain.Role;
import com.security.auth.server.domain.User;
import com.security.auth.server.handler.password.PasswordChangeHandler;
import com.security.auth.server.handler.password.PasswordInitialiserHandler;
import com.security.auth.server.handler.role.RolePersistantHandler;
import com.security.auth.server.handler.user.UserHandler;
import com.security.auth.server.repo.PermissionRepo;
import com.security.auth.server.repo.RoleRepository;
import com.security.auth.server.repo.UserRepository;
import com.sim.config.validation.ValidationConfig;
import com.sim.handler.api.Handler;
import com.sim.handler.impl.PersistanceHandler;
import com.sim.validator.api.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import({DataConfig.class, ValidationConfig.class})
public class PersistanceServiceConfig {

    @Bean
    public Handler<User> userHandler(final Validator validator,
                                     final UserRepository userRepository,
                                     final RoleRepository roleRepository) {
        return new UserHandler(userRepository, validator, roleRepository);
    }

    @Bean
    public Handler<User> passwordInitialiserHandler(final Validator validator,
                                                    final UserRepository userRepository,
                                                    final PasswordEncoder passwordEncoder,
                                                    final RoleRepository roleRepository) {
        return new PasswordInitialiserHandler(userRepository, passwordEncoder, validator, roleRepository);
    }

    @Bean
    public Handler<User> passwordChangeHandler(final Validator validator,
                                               final UserRepository userRepository,
                                               final PasswordEncoder passwordEncoder) {
        return new PasswordChangeHandler(userRepository, validator, passwordEncoder);
    }


    @Bean
    public Handler<Role> rolePersistHandler(final Validator validator,
                                            final RoleRepository roleRepository,
                                            final PermissionRepo permissionRepo) {
        return new RolePersistantHandler(roleRepository, permissionRepo, validator);
    }

    @Bean
    public Handler<Permission> permissionHandlerPersistHandler(final Validator validator,
                                                               final PermissionRepo permissionRepo) {
        return new PersistanceHandler<>(permissionRepo, validator);
    }
}
