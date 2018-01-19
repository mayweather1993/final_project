package com.homework.project.repositories;

import com.homework.project.FinalApplication;
import com.homework.project.domain.Department;
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
public class DepartmentRepositoryTest {
    Department department = new Department(null,"Department_Name");

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() throws Exception {
        department.setCreatedBy("I");
        department.setCreatedDate(Instant.now());
        department.setLastModifiedBy("Inna");
        department.setLastModifiedDate(Instant.now());
    }

    @Test
    public void saveDepartmentTest() {

        Department result1 = departmentRepository.save(department);
        Assert.assertNotNull(result1.getId());
    }

    @Test
    public void findDepartmentByIdTest() {
        Department result1 = departmentRepository.save(department);
        Department result2 = departmentRepository.getOne(result1.getId());
        Assert.assertNotNull(result2);
    }
}
