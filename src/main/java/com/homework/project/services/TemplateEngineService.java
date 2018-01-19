package com.homework.project.services;

import com.homework.project.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TemplateEngineService {

    private final ApplicationProperties properties;

    public String generateMessage(final String templateName, final List<String> parameters) {
        log.info("Generating message for template [{}] with parameters [{}]", templateName, parameters);
        final String template = properties.getTemplates().get(templateName);
        return String.format(template, parameters.toArray(new String[0]));
    }

}
