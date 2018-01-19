package com.homework.project.services;

import com.homework.project.domain.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionsService extends CrudService<Position> {
    Page<Position> getPositions(Pageable pageable);

    Position findById(final Long id);
}
