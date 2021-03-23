package com.kalus.demo.conf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  No setter found for property: name
 *  这样写需要提供setter方法
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class NeoP2 {

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
