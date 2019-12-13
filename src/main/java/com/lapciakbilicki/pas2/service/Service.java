package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.IsIdentified;

import java.util.List;
import java.util.function.Predicate;

public interface Service<T extends IsIdentified> {

    boolean add(T item);

    T get(String id);

    List<T> getAll();

    void update(T item);

    boolean remove(T item);

    List<T> getByCondition(Predicate<T> predicate);

}
