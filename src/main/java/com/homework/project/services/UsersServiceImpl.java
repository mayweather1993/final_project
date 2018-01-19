package com.homework.project.services;

import com.homework.project.domain.Department;
import com.homework.project.domain.Position;
import com.homework.project.domain.User;
import com.homework.project.exceptions.NotAuthorizedException;
import com.homework.project.exceptions.ResourceNotFoundException;
import com.homework.project.repositories.UserRepository;
import com.homework.project.rest.vm.UserVM;
import com.homework.project.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final DepartmentsService departmentsService;
    private final PositionsService positionsService;
    private final ReceiptsService receiptsService;
    private final PasswordEncoder passwordEncoder;

    private final Function<UserVM, User> userVmToUserFunction = userVM -> {
        User user = new User();
        user.setFirstName(userVM.getFirstName());
        user.setLastName(userVM.getLastName());
        user.setEmail(userVM.getEmail());
        user.setPassword(userVM.getPassword());
        user.setHourSalary(userVM.getHourSalary());
        user.setStatus(userVM.getStatus());
        user.setType(userVM.getType());
        return user;
    };


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getCurrentUser() {
        final String currentUserEmail = SecurityUtils.getCurrentUserLogin().orElseThrow(NotAuthorizedException::new);
        return findByEmail(currentUserEmail);
    }

    @Override
    public Double getCurrentUserSalary(final Instant from, final Instant to) {
        final User currentUser = getCurrentUser();
        return getUserSalary(currentUser.getId(), from, to);
    }

    @Override
    public Double getUserSalary(final Long userId, final Instant from, final Instant to) {
        return receiptsService.getSalaryForUser(userId, from, to);
    }

    @Override
    public User userVmToUser(final UserVM userVM) {
        final User user = userVmToUserFunction.apply(userVM);
        final Long departmentId = userVM.getDepartment();
        final Long positionId = userVM.getPosition();
        if (departmentId != null) {
            final Department department = departmentsService.findById(departmentId);
            user.setDepartment(department);
        }
        if (positionId != null) {
            final Position position = positionsService.findById(positionId);
            user.setPosition(position);
        }
        return user;
    }

    @Override
    public User findById(final Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Long create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        User user = userRepository.save(entity);
        return user.getId();
    }

    @Override
    public void update(Long id, User updated) {
        User user = userRepository.getOne(id);
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setEmail(updated.getEmail());
        user.setPassword(passwordEncoder.encode(updated.getPassword()));
        user.setDepartment(updated.getDepartment());
        user.setHourSalary(updated.getHourSalary());
        user.setPosition(updated.getPosition());
        user.setStatus(updated.getStatus());
        user.setType(updated.getType());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
