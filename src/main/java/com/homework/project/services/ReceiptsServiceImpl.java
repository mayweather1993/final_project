package com.homework.project.services;

import com.homework.project.domain.Receipt;
import com.homework.project.repositories.ReceiptsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ReceiptsServiceImpl implements ReceiptsService {

    private final ReceiptsRepository receiptsRepository;

    @Override
    public Long create(Receipt entity) {
        Receipt receipt = receiptsRepository.save(entity);
        return receipt.getId();
    }

    @Override
    public void update(Long id, Receipt updated) {
        Receipt receipt = receiptsRepository.getOne(id);
        receipt.setSalary(updated.getSalary());
        receipt.setUserId(updated.getUserId());
        receiptsRepository.save(receipt);
    }

    @Override
    public void delete(Long id) {
        receiptsRepository.deleteById(id);
    }

    @Override
    public Double getSalaryForUser(final Long userId, final Instant from, final Instant to) {
        final Instant fromDate = from == null ? Instant.EPOCH : from;
        final Instant toDate = to == null ? Instant.now() : to;
        return receiptsRepository.getSalaryForUser(userId, fromDate, toDate);
    }

    @Override
    public Receipt findById(final Long id) {
        return receiptsRepository.getOne(id);
    }
}
