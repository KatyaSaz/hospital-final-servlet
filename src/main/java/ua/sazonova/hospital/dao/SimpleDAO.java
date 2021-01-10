package ua.sazonova.hospital.dao;

import java.util.List;

public interface SimpleDAO<T> {
    void delete(int id);
    int getUserId(int id);
    T getById(int id, String lang);
    List<T> getAll(String lang);
    List<T> getNonActive(String lang);
    List<T> sort(String request, String lang);
}
