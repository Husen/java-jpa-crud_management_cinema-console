package org.enigma.service;

import java.util.List;

public interface IService<T> {
    void create(T params);
    void update(T params);
    void delete(int id);

    List<T> getAll(int page, int size);
    T getById(int id);
}
