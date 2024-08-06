package com.piggy.customers.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ApacheConfiguration {

    @Bean
    public NewTopic consumerOrder(){
        return TopicBuilder.name("foodOrder")
                .build();
    }

}