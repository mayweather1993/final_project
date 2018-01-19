package com.homework.project.repositories;

import com.homework.project.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface ReceiptsRepository extends JpaRepository<Receipt, Long> {
    Double getSalaryForUser(@Param("userId") final Long userId,
                            @Param("from") final Instant from,
                            @Param("to") final Instant to);
}
