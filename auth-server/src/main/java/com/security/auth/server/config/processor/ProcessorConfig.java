package com.security.auth.server.config.processor;



import com.security.auth.server.config.mapper.MapperConfig;
import com.security.auth.server.config.handler.QueryServiceConfig;
import com.sim.config.handler.factory.HandlerFactoryConfig;
import com.sim.factory.api.HandlerFactory;
import com.sim.mapper.api.Mapper;
import com.sim.processor.api.Processor;
import com.sim.processor.impl.ProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.security.auth.server","com.sim.aspect"})
@Import({QueryServiceConfig.class, MapperConfig.class, HandlerFactoryConfig.class})
public class ProcessorConfig {

    @Bean
    public Processor processor(final Mapper mapper, final HandlerFactory handlerFactory) {
        return new ProcessorImpl(mapper, handlerFactory);
    }

}
