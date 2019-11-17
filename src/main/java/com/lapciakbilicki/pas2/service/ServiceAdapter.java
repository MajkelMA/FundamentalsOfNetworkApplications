package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.IsIdentified;
import com.lapciakbilicki.pas2.repository.Repository;

import java.util.List;
import java.util.function.Predicate;

public abstract class ServiceAdapter<T extends IsIdentified> implements Service<T> {

    protected Repository<T> repository;

    @Override
    public boolean add(T item) {
        return this.repository.add(item);
    }

    @Override
    public T get(String id) {
        return this.repository.get(id);
    }

    @Override
    public List<T> getAll() {
        return this.repository.getAll();
    }

    @Override
    public void update(T item) {
        this.repository.update(item);
    }

    @Override
    public boolean remove(T item) {
        return this.repository.remove(item);
    }

    @Override
    public List<T> getByCondition(Predicate<T> predicate) {
        return this.repository.getByCondition(predicate);
    }
}
