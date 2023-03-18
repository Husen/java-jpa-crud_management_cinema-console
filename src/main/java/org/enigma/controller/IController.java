package org.enigma.controller;

import java.util.List;

public interface IController<T> {
    void add();
    void update();
    void delete();

    List<T> getAll();
    T getById();
}
