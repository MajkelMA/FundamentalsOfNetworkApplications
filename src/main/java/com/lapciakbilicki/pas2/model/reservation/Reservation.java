package com.lapciakbilicki.pas2.model.reservation;

import com.lapciakbilicki.pas2.model.IsIdentified;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import java.util.Date;
import java.util.Objects;

public class Reservation implements IsIdentified {

    private String id;
    private Account account;
    private SportsFacility facility;
    private Date startDate;
    private Date endDate;
    private Date finishDate;
    private boolean active;

    public Reservation() {

    }

    public Reservation(String id, Account account, SportsFacility facility, Date startDate, Date endDate, boolean active) {
        this.id = id;
        this.account = account;
        this.facility = facility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.finishDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //<editor-fold desc="getters and setters">
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
