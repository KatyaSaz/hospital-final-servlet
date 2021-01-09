package ua.sazonova.hospital.dao;

import java.util.List;

public interface SimpleDAO<T> {
    void create(T object);
    void delete(int id);
    int getUserId(int id);
    T getById(int id, String lang);
    List<T> getAll();
    List<T> getNonActive();
    List<T> sort(String request);
}
