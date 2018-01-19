package com.homework.project.rest;

import com.homework.project.domain.Position;
import com.homework.project.services.PositionsService;
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
@RequestMapping("/positions")
@AllArgsConstructor
public class PositionRestController {

    private final PositionsService positionsService;

    @PostMapping
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity post(@RequestBody Position position) {
        positionsService.create(position);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Position position) {
        positionsService.update(id, position);
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getPositions(Pageable pageable) {
        final Page<Position> positions = positionsService.getPositions(pageable);
        return new ResponseEntity<>(positions, OK);
    }

}
