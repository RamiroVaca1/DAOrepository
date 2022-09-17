package com.solvd.DAO;

import java.util.List;

public interface GenericDAO<T, K> {

    void insert(T a);

    void update(T a);

    void delete(T a);

    T getOne(K id);

    List<T> getAll();



}
