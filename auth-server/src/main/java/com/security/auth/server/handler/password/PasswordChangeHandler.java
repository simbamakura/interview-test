package com.security.auth.server.handler.password;

import com.security.auth.server.domain.User;
import com.security.auth.server.util.SysConstansts;
import com.sim.data.repo.api.BaseRepo;
import com.sim.exception.ServiceException;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractDefaultHandler;
import com.sim.validator.api.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class PasswordChangeHandler extends AbstractDefaultHandler<User> {

    public static final String USE_CASE = "password-change-handler";
    private final PasswordEncoder passwordEncoder;

    public PasswordChangeHandler(BaseRepo<User> baseRepo, Validator validator,
                                 PasswordEncoder passwordEncoder) {
        super(baseRepo, validator);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected User execute(HandlerContext context) {
        validator.validate(context, SysConstansts.OLD_PASSWORD);
        validator.validate(context, SysConstansts.NEW_PASSWORD);

        Map<String, String> additionalData = context.getAdditionalData();

        String rawOldPassword = additionalData.get(SysConstansts.OLD_PASSWORD);
        String encodedNewassword = passwordEncoder.encode(additionalData.get(SysConstansts.NEW_PASSWORD));

        User user = getBaseEntity(context);
        User savedUser = baseRepo.getOne(user.getId());
        String savedPassword = savedUser.getPassword();


        if (!passwordEncoder.matches(rawOldPassword, savedPassword)) {
            throw new ServiceException("Old Password and saved password do not match");
        }
        savedUser.setPassword(encodedNewassword);
        return baseRepo.save(savedUser);
    }
}
