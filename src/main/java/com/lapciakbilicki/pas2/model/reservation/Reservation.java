package com.lapciakbilicki.pas2.model.reservation;

import com.lapciakbilicki.pas2.model.IsIdentified;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Reservation implements IsIdentified {
    private String id;
    private Account account;
    private SportsFacility facility;
    private Date startDate;
    private Date endDate;

    public Reservation(String id, Account account, SportsFacility facility, Date startDate, Date endDate) {
        this.id = id;
        this.account = account;
        this.facility = facility;
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    public double getPrice() {
//        long time = endDate.getTimeInMillis() - startDate.getTimeInMillis();
//        return ((double) time) * (facility.getPricePerHours() / 3600000);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //<editor-fold desc="setters">
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setFacility(SportsFacility facility) {
        this.facility = facility;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
//</editor-fold>

    //<editor-fold desc="getters">
    @Override
    public String getId() {
        return this.id;
    }

    public Account getAccount() {
        return account;
    }

    public SportsFacility getFacility() {
        return facility;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
//</editor-fold>
}
