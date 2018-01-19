package com.homework.project.repositories;

import com.homework.project.FinalApplication;
import com.homework.project.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = FinalApplication.class)
public class UserRepositoryTest {


    User user = new User((long) 1, "pasha@pasha.com", "qq", "pasha", "kozachek", false, null, null, 0.0, null, null);


    @Autowired
    private UserRepository userRepository;

    @Test
    public void testingSaveUser() {
        User result = userRepository.save(user);
        Assert.assertNotNull(result.getId());
    }

    @Test
    public void testingFindByIdUser() {
        User result = userRepository.save(user);
        User result2 = userRepository.getOne(result.getId());
        Assert.assertNotNull(result2);
    }
}
