package com.kalus.demo;

import com.kalus.demo.conf.properties.NeoP2;
import com.kalus.demo.conf.properties.NeoP3;
import com.kalus.demo.conf.properties.NeoProperties;
import com.kalus.demo.model.User;
import com.kalus.demo.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.SpringVersion;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
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

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
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

//        userRepository.save(new User("k1", "p1", "ybw", "ybw"));

        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.opsForValue().get("aaa");

        User user = new User("aa@126.com", "Kalus", "123456", "yyy@edde.com", "cshit");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.edde", user);
        operations.set("com.edde.f", user, 1, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (redisTemplate.hasKey("com.edde.f")) {
            sb.append(", exists is true");
        }


        return sb.toString();
    }

    @RequestMapping(value = "redis", method = RequestMethod.GET)
    @Cacheable(value = "user-key")
    public String redisTest() {
        User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user.toString();
    }

    @RequestMapping(value = "shit", method = RequestMethod.GET)
    @ResponseBody
    public String test2(@RequestParam(value = "userName") String userName) {
        return "Hello," + userName + ",it's a Shit-Company";
    }

    @ResponseBody
    @RequestMapping("/uid")
    public String uid(HttpSession httpSession) {

        UUID uuid = (UUID) httpSession.getAttribute("uid");
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        httpSession.setAttribute("uid", uuid);
        return httpSession.getId();

    }


    /**
     * 不需要 @ResponseBody
     * @param modelMap
     * @return
     */
    @RequestMapping("/switch")
    public String switchcase(ModelMap modelMap) {
        modelMap.addAttribute("sex","woman");
        return "switch";
    }
}
