package com.kalus.demo.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 有多种配置方式
 */
@Component
public class NeoProperties {

    @Value("${com.kalus.title}")
    private String title;

    @Value("${com.kalus.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
