package ua.sazonova.hospital.dao;

import java.util.List;

public interface SimpleDAO<T> {
    void create(T object);
    void delete(int id);
//    void update(T object);
    int getUserId(int id);
    T getById(int id);
    List<T> getAll();
    List<T> getNonActive();
}
