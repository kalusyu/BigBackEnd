package com.kalus.demo;

import com.kalus.demo.model.User;
import com.kalus.demo.service.UserRepository;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        userRepository.save(new User("k1", "p1", "ybw", "ybw"));
        userRepository.save(new User("k2", "p2", "ybw", "ybw"));
        userRepository.save(new User("k3", "p3", "ybw", "ybw"));

        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("k2", userRepository.findByUserName("k2").getUserName());


    }
}
