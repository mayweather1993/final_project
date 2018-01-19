package com.homework.project.services;

import com.homework.project.domain.Position;
import com.homework.project.repositories.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionsServiceImpl implements PositionsService {

    private final PositionRepository positionRepository;

    @Override
    public Long create(Position entity) {
        Position position = positionRepository.save(entity);
        return position.getId();
    }

    @Override
    public void update(Long id, Position updated) {
        Position position = positionRepository.getOne(id);
        position.setName(updated.getName());
        positionRepository.save(position);
    }

    @Override
    public void delete(Long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Page<Position> getPositions(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    public Position findById(final Long id) {
        return positionRepository.getOne(id);
    }
}
