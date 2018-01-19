package com.homework.project.services;

import com.homework.project.domain.User;
import com.homework.project.rest.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface UsersService extends CrudService<User> {
    User findByEmail(String email);

    Page<User> findUsers(Pageable pageable);

    User getCurrentUser();

    Double getCurrentUserSalary(Instant from, Instant to);

    Double getUserSalary(Long userId, Instant from, Instant to);

    User userVmToUser(final UserVM userVM);

    User findById(final Long id);
}
