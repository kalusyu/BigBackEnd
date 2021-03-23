package com.kalus.demo;

import com.kalus.demo.conf.properties.NeoP2;
import com.kalus.demo.conf.properties.NeoP3;
import com.kalus.demo.conf.properties.NeoProperties;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "shit")
public class DemoController {

    // 自定义属性 application.properties中定义，以下是使用方式
    @Resource
    NeoProperties properties;

    @Resource
    NeoP2 neoP2;

    @Resource
    NeoP3 neoP3;

    // 使用了属性name = "" 导致无法访问
    @RequestMapping(value = "hello")
    @ResponseBody
    public String test() {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();

        String title = properties.getTitle();
        String desc = properties.getDescription();
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, SpringBoot-Shit " + springVersion + "," + springBootVersion);
        sb.append("\n title = " + title + ", desc = " + desc);
        sb.append("\r name=" + neoP2.getName() + ",sex = " + neoP2.getSex());
        sb.append("\r name3=" + neoP3.getName());
        return sb.toString();
    }

    @RequestMapping(value = "shit", method = RequestMethod.GET)
    @ResponseBody
    public String test2(@RequestParam(value = "userName") String userName) {
        return "Hello," + userName + ",it's a Shit-Company";
    }
}
