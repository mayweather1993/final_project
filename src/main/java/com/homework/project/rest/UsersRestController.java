package com.homework.project.rest;

import com.homework.project.domain.User;
import com.homework.project.domain.UserType;
import com.homework.project.exceptions.NoPermissionsException;
import com.homework.project.rest.vm.UserVM;
import com.homework.project.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import static com.homework.project.SecurityConstants.HAS_ADMIN_MODERATOR_AUTHORITY;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersRestController {

    private final UsersService usersService;

    @PostMapping
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity<UserVM> post(@RequestBody final UserVM userVM) {
        final User user = usersService.userVmToUser(userVM);
        checkPermissions(user);
        usersService.create(user);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody UserVM userVm) {
        User user = usersService.userVmToUser(userVm);
        checkPermissions(user);
        usersService.update(id, user);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        checkPermissions(usersService.findById(id));
        usersService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("{id}")
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getById(@PathVariable("id") final Long id) {
        final User user = usersService.findById(id);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("{id}/salary")
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getSalaryByUserId(@PathVariable("id") final Long id,
                                            @RequestParam(value = "from", required = false) Instant from,
                                            @RequestParam(value = "to", required = false) Instant to) {
        final Double salary = usersService.getUserSalary(id, from, to);
        return new ResponseEntity<>(salary, OK);
    }

    @GetMapping("/current")
    public ResponseEntity getCurrent() {
        final User currentUser = usersService.getCurrentUser();
        return new ResponseEntity<>(currentUser, OK);
    }

    @GetMapping("/current/salary")
    public ResponseEntity getCurrentUserSalary(@RequestParam(value = "from", required = false) Instant from,
                                               @RequestParam(value = "to", required = false) Instant to) {
        final Double currentUserSalary = usersService.getCurrentUserSalary(from, to);
        return new ResponseEntity<>(currentUserSalary, OK);
    }

    @GetMapping
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getUsers(final Pageable pageable) {
        final Page<User> users = usersService.findUsers(pageable);
        return new ResponseEntity<>(users, OK);
    }

    private void checkPermissions(User user) {
        final UserType type = user.getType();
        if (type != UserType.ADMIN) {
            return;
        }
        final User currentUser = usersService.getCurrentUser();
        final UserType currentUserType = currentUser.getType();
        if (currentUserType == UserType.ADMIN) {
            return;
        }
        throw new NoPermissionsException();
    }

}
