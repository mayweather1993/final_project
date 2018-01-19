package com.homework.project;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Validated
@Data
public class ApplicationProperties {
    @Positive
    private int corePoolSize;
    @Positive
    private int maxPoolSize;
    @Positive
    private int queueCapacity;
    @NotEmpty
    private String from;
    private Map<String, String> templates = new HashMap<>();
}
