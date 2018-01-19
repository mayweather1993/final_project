package com.homework.project.rest;

import com.homework.project.domain.Department;
import com.homework.project.services.DepartmentsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.homework.project.SecurityConstants.HAS_ADMIN_AUTHORITY;
import static com.homework.project.SecurityConstants.HAS_ADMIN_MODERATOR_AUTHORITY;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentsRestController {
    private final DepartmentsService departmentsService;

    @PostMapping
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity post(@RequestBody Department department) {
        departmentsService.create(department);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Department department) {
        departmentsService.update(id, department);
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getDepartments(final Pageable pageable) {
        Page<Department> departmentsPage = departmentsService.getDepartments(pageable);
        return new ResponseEntity<>(departmentsPage, OK);
    }

}
