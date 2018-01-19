package com.homework.project.services;

import com.homework.project.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentsService extends CrudService<Department> {
    Page<Department> getDepartments(Pageable pageable);

    Department findById(final Long id);
}
