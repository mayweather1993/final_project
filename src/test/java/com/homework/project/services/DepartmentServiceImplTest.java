package com.homework.project.services;

import com.homework.project.domain.Department;
import com.homework.project.repositories.DepartmentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DepartmentServiceImplTest {

    private static final String NAME = "Pasha";
    private static final Long ID = 1L;
    @Mock
    private DepartmentRepository departmentRepository;
    private Department department;

    @Autowired
    DepartmentsService departmentService;


    @Before
    public void setup() {
        department = new Department(ID, NAME);

        MockitoAnnotations.initMocks(this);
    }
    @Before
    public void setUp() throws Exception {
        department.setCreatedBy("I");
        department.setCreatedDate(Instant.now());
        department.setLastModifiedBy("Inna");
        department.setLastModifiedDate(Instant.now());
    }


    @Test
    @Transactional
    public void create() {
        when(departmentRepository.save(department)).thenReturn(department);
        Long result = departmentService.create(department);
        Assert.assertEquals(ID, result);

    }

    @Test
    @Transactional
    public void update() {
        departmentService.create(department);
        department.setName("NePasha");
        departmentService.update(ID , department);
        departmentRepository.save(department);
        Assert.assertEquals(department.getName() , "NePasha");

    }

    @Test
    public void deleteDepartment() {
        departmentService.create(department);
        departmentRepository.save(department);
        Long id = department.getId();
        departmentService.delete(id);
        verify(departmentRepository, times(1)).delete(department);
    }
}