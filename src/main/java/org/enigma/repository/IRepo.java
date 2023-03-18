package org.enigma.repository;

import java.util.List;

public interface IRepo<T> {
    void create(T params);
    void update(T params);
    void delete(T params);

    List<T> getAll(int page, int size);
    T getById(int id);
}
