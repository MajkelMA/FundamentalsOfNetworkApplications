package com.lapciakbilicki.pas2.repository;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {

    boolean add(T item);

    T get(String id);

    List<T> getAll();

    boolean update(T item);

    boolean remove(T item);

    List<T> getByCondition(Predicate<T> predicate);
}
