package com.kalus.demo.conf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom2")
// 这里的classpath 指定到了 resources目录下,配置文件是有顺序的，先加载application.properties再加载自定义配置
@PropertySource("classpath:config/config.properties")
public class NeoP3 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
