package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.Filler;
import com.lapciakbilicki.pas2.model.IsIdentified;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public abstract class RepositoryAdapter<T extends IsIdentified> implements Repository<T> {


    private List<T> listOfItems;
    private Filler<T> filler;

    @Override
    public boolean add(T item) {
        if(item.getId() !=null){
            if(this.get(item.getId()) == null){
                this.listOfItems.add(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(String id) {
        return this.listOfItems.stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<T> getAll() {
        return listOfItems;
    }

    @Override
    public abstract void update(T item);

    @Override
    public boolean remove(T item) {
        return this.listOfItems.remove(item);
    }

    @Override
    public List<T> getByCondition(Predicate<T> predicate){
        return this.listOfItems.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<T> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<T> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public Filler<T> getFiller() {
        return filler;
    }

    public void setFiller(Filler<T> filler) {
        this.filler = filler;
    }
}
