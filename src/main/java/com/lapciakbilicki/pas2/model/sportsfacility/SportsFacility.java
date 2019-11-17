package com.lapciakbilicki.pas2.model.sportsfacility;

import com.lapciakbilicki.pas2.model.IsIdentified;
import com.lapciakbilicki.pas2.model.account.Account;

import java.util.Objects;

public abstract class SportsFacility implements IsIdentified{
    private String id;
    private double pricePerHours;
    private boolean access;
    private double penalty;
    private Field field;

    public SportsFacility(){

    }

    public SportsFacility(String id, double pricePerHours, boolean access, double penalty, Field field) {
        this.id = id;
        this.pricePerHours = pricePerHours;
        this.access = access;
        this.penalty = penalty;
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPricePerHours() {
        return pricePerHours;
    }

    public void setPricePerHours(double pricePerHours) {
        this.pricePerHours = pricePerHours;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsFacility that = (SportsFacility) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void copyAttributionsWithoutId(SportsFacility sportsFacility){
        this.pricePerHours = sportsFacility.pricePerHours;
        this.access = sportsFacility.access;
        this.field = sportsFacility.field;;
        this.penalty = sportsFacility.penalty;
    }
}
