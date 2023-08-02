package com.security.auth.server.handler.role;

import com.security.auth.server.domain.Role;
import com.security.auth.server.repo.RoleRepository;
import com.sim.data.repo.api.BaseRepo;
import com.sim.handler.domain.HandlerContext;
import com.sim.handler.impl.AbstractQueryHandler;
import com.sim.util.AdditionalDataUtil;
import com.sim.validator.api.Validator;
import org.springframework.data.domain.Page;

public class RoleQueryHandler extends AbstractQueryHandler<Role> {

    public static final String USE_CASE = "role-query";

    public RoleQueryHandler(BaseRepo<Role> baseRepo, Validator validator) {
        super(baseRepo, validator);
    }

    @Override
    public Page<Role> excuteQuery(HandlerContext context) {
        String query = AdditionalDataUtil.getQuery(context.getAdditionalData());
        return ((RoleRepository) baseRepo).searchRole(query, context.getPageable());
    }


}
