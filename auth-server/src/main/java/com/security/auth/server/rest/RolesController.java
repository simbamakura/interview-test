package com.security.auth.server.rest;

import com.security.auth.server.dto.RoleDto;
import com.security.auth.server.handler.role.RolePersistantHandler;
import com.security.auth.server.handler.role.RoleQueryHandler;
import com.security.auth.server.util.SysConstansts;
import com.sim.DefaultSysConstants;
import com.sim.dto.ResponseDto;
import com.sim.processor.api.Processor;
import com.sim.util.DtoUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("security")
public class RolesController {

    private final Processor processor;

    public RolesController(Processor processor) {
        this.processor = processor;
    }

    @PostMapping(value = "role", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseDto<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        Map<String, Object> additionalData = DtoUtil.getAdditionalData(roleDto.getAdditionalData());
        roleDto.setAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, RolePersistantHandler.USE_CASE);
        return processor.process(roleDto);
    }

    @GetMapping(value = "role", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseDto<RoleDto> getRoles(@RequestParam Map<String, Object> additionalData,
                                         Pageable pageable) {
        additionalData = DtoUtil.getAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, RoleQueryHandler.USE_CASE);
        return processor.process(additionalData, pageable);
    }

    @GetMapping(value = "role-id",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<RoleDto> getRevenueActivityAvgDto(@RequestParam Map<String, Object> additionalData,
                                                         Pageable pageable) {
        additionalData = DtoUtil.getAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, SysConstansts.QUERY_ROLES_BY_ID);
        return processor.process(additionalData, pageable);
    }
}
