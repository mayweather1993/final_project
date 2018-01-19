package com.homework.project.services;

import com.homework.project.domain.Department;
import com.homework.project.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentsService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Long create(Department entity) {
        Department department = departmentRepository.save(entity);
        return department.getId();
    }

    @Override
    public void update(Long id, Department updated) {
        Department department = departmentRepository.getOne(id);
        department.setName(updated.getName());
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Page<Department> getDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.getOne(id);
    }
}
