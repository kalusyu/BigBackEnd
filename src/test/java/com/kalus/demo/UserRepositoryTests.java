package com.kalus.demo;

import com.kalus.demo.model.User;
import com.kalus.demo.service.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        userRepository.save(new User("aa@126.com", "k1", "p1", "ybw", "ybw"));
        userRepository.save(new User("aa@126.com", "k2", "p2", "ybw", "ybw"));
        userRepository.save(new User("aa@126.com", "k3", "p3", "ybw", "ybw"));

        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("k2", userRepository.findByUserName("k2").getUserName());


    }
}
