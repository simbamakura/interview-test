package com.security.auth.server.handler.user;

import com.security.auth.server.domain.User;
import com.security.auth.server.repo.UserRepository;
import com.sim.data.repo.api.BaseRepo;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractQueryHandler;
import com.sim.util.AdditionalDataUtil;
import com.sim.validator.api.Validator;
import org.springframework.data.domain.Page;

public class UserQueryHandler extends AbstractQueryHandler<User> {

    public static final String USE_CASE = "user-query";

    public UserQueryHandler(BaseRepo<User> baseRepo, Validator validator) {
        super(baseRepo, validator);
    }

    @Override
    public Page<User> excuteQuery(HandlerContext context) {
        String query = AdditionalDataUtil.getQuery(context.getAdditionalData());
        return ((UserRepository) baseRepo).searchUser(query, context.getPageable());
    }
}
