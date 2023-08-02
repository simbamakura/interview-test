package com.security.auth.server.config.mapper;


import com.sim.mapper.api.Mapper;
import com.sim.mapper.api.MapperImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.security.auth.server")
public class MapperConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        final List<String> mappingFiles = new ArrayList<>();
        //mappingFiles.add("mapper.xml");
        DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
        return mapper;
    }

    @Bean
    public Mapper mapper(final DozerBeanMapper dozerBeanMapper) {
        return new MapperImpl(dozerBeanMapper);
    }


}
