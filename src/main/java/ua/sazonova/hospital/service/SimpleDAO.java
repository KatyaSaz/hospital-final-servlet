package ua.sazonova.hospital.service;

import java.sql.Connection;
import java.util.List;

public interface SimpleDAO<T> {
    void create(T object);
    void delete(int id);
//    void update(T object);
    T getById(int id);
    List<T> getAll();
    List<T> getNonActive();
}
