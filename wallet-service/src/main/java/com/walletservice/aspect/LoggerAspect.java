package com.walletservice.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.walletservice.dto.ResponseDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String LOG_PREFIX = "-----------------";

    @Around("@within(com.walletservice.annotations.logger.Logger)) || @annotation(com.jmrassessmenttest.annotations.logger.Loggerr)")
    public ResponseDto performLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        logRequestDetails(joinPoint);

        ResponseDto responseDto;
        try {
            responseDto = (ResponseDto) joinPoint.proceed();
        } catch (Exception ex) {
            responseDto = handleException(ex);
        }

        logResponseDetails(joinPoint, responseDto);
        return responseDto;
    }

    private void logRequestDetails(ProceedingJoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        LOGGER.info("{} Incoming request for [{}]", LOG_PREFIX, codeSignature);
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = codeSignature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            LOGGER.info("{} [{}] - [{}]", LOG_PREFIX, GSON.toJson(parameterNames[i]), GSON.toJson(args[i]));
        }
    }

    private ResponseDto handleException(Exception ex) {
        LOGGER.error("Exception occurred", ex);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setNarrative(ex.getMessage());
        responseDto.setSuccess(false);
        return responseDto;
    }

    private void logResponseDetails(ProceedingJoinPoint joinPoint, ResponseDto responseDto) {
        LOGGER.info("{} Outgoing response for [{}]", LOG_PREFIX, joinPoint.getSignature().toString());
        LOGGER.info("{} [{}]", LOG_PREFIX, GSON.toJson(responseDto));
    }
}
