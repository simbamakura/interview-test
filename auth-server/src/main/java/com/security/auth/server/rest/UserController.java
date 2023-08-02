package com.security.auth.server.rest;


import com.security.auth.server.dto.PermissionDto;
import com.security.auth.server.dto.UserDto;
import com.security.auth.server.handler.password.PasswordChangeHandler;
import com.security.auth.server.handler.password.PasswordInitialiserHandler;
import com.security.auth.server.handler.user.UserHandler;
import com.security.auth.server.handler.user.UserQueryHandler;
import com.sim.DefaultSysConstants;
import com.sim.annotations.RequestLogger;
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
//@PreAuthorize("permitAll()")
@RequestLogger
public class UserController {

    private final Processor processor;

    public UserController(Processor processor) {
        this.processor = processor;
    }

    @PostMapping(value = "user", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('role_admin')")
    public ResponseDto<UserDto> saveUser(@RequestBody UserDto userDto) throws InterruptedException {
        Map<String, Object> additionalData = DtoUtil.getAdditionalData(userDto.getAdditionalData());
        userDto.setAdditionalData(additionalData);
        Thread.sleep(3000);
        additionalData.put(DefaultSysConstants.USE_CASE, UserHandler.USE_CASE);
        return processor.process(userDto);
    }

    @GetMapping(value = "users", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("permitAll()")
    @RequestLogger
    public ResponseDto<UserDto> getUsers(@RequestParam Map<String, Object>  additionalData,
                                         Pageable pageable) throws InterruptedException {
        additionalData = DtoUtil.getAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, UserQueryHandler.USE_CASE);
        Thread.sleep(3000);
        return processor.process(additionalData, pageable);
    }

    @PostMapping(value = "initialise-user", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<UserDto> initialiseUser(@RequestBody UserDto userDto) {
        Map<String, Object>  additionalData = DtoUtil.getAdditionalData(userDto.getAdditionalData());
        userDto.setAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, PasswordInitialiserHandler.USE_CASE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return processor.process(userDto);
    }

    @PostMapping(value = "reset-password", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<UserDto> resetPassword(@RequestBody UserDto userDto) {
        Map<String, Object>  additionalData =  DtoUtil.getAdditionalData(userDto.getAdditionalData());
        userDto.setAdditionalData(additionalData);
        additionalData.put(DefaultSysConstants.USE_CASE, PasswordChangeHandler.USE_CASE);
        return processor.process(userDto);
    }

    @PostMapping(value = "permissions", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('role_admin')")
    public ResponseDto<PermissionDto> savePermission(@RequestBody PermissionDto dto) {
        return processor.process(dto);
    }


//    @GetMapping(value = "permissions", consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @PreAuthorize("hasAuthority('role_admin')")
//    public ResponseDto<PermissionDto> getPermission(@RequestParam(value = "search_query", required = false) String search,
//                                                    Pageable pageable) {
//        return  processor.process(search, pageable);
//    }
}
