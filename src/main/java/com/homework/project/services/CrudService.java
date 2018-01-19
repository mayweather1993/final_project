package com.homework.project.services;

public interface CrudService<E> {
    Long create(E entity);

    void update(Long id, E updated);

    void delete(Long id);
}
