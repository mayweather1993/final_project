package com.homework.project.repositories;

import com.homework.project.FinalApplication;
import com.homework.project.domain.EventUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinalApplication.class)
public class EventUserRepositoryTest {
    EventUser eventUser = new EventUser(null, 0L, 0L, 0L);

    @Autowired
    private EventUserRepository eventUserRepository;

    @Before
    public void setUp() throws Exception {
        eventUser.setCreatedBy("I");
        eventUser.setCreatedDate(Instant.now());
        eventUser.setLastModifiedBy("Inna");
        eventUser.setLastModifiedDate(Instant.now());
    }

    @Test
    public void saveEventUser() {
        EventUser result = eventUserRepository.save(eventUser);
        Assert.assertNotNull(result);
    }

    @Test
    public void findEventUserByIdTest() {
        EventUser result1 = eventUserRepository.save(eventUser);
        EventUser result2 = eventUserRepository.getOne(result1.getId());
        Assert.assertNotNull(result2);
    }
}
