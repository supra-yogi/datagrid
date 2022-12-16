package com.yogi.demodatagrid.config;


import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class JDGConfiguration {


    @Value(value = "${jdg.server.location}")
    private String jdgServerLocation;
    @Value(value = "${jdg.hotrod.port}")
    private int jdgHotRodPort;

    @Value(value = "${jdg.username}")
    private String jdgUsername;

    @Value(value = "${jdg.password}")
    private String jdgPassword;

    @Bean
    public InfinispanRemoteConfigurer infinispanRemoteConfigurer() {
        return () -> new ConfigurationBuilder()
                .addServer()
                .host(jdgServerLocation)
                .port(jdgHotRodPort)
                .security().authentication()
                .username(jdgUsername)
                .password(jdgPassword)
                .addJavaSerialAllowList("com.yogi.demodatagrid.bean.*")
                .marshaller(JavaSerializationMarshaller.class)
                .build();
    }

}