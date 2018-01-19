package com.homework.project.services;

import com.homework.project.domain.Receipt;

import java.time.Instant;

public interface ReceiptsService extends CrudService<Receipt> {

    Double getSalaryForUser(final Long userId, final Instant from, final Instant to);

    Receipt findById(final Long id);

}
