package com.lapciakbilicki.pas2.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    boolean add(T item);
    T get(String id);
    List<T> getAll();
    void update(T item);
    boolean remove(T item);
    List<T> getByCondition(Predicate<T> predicate);
}
