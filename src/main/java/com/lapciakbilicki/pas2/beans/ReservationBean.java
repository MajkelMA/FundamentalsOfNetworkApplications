package com.lapciakbilicki.pas2.beans;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.ReservationService;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@FlowScoped("reservation")
public class ReservationBean implements Serializable {
    private Account account;
    private SportsFacility sportsFacility;
    String startDate, startHour, endDate, endHour;

    @Inject
    AccountService accountService;
    @Inject
    SportsFacilityService sportsFacilityService;
    @Inject
    ReservationService reservationService;

    @PostConstruct
    public void init() {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, y", Locale.US);
        this.startDate = sdf.format(now);
        this.endDate = this.startDate;
        sdf = new SimpleDateFormat("H");
        this.startHour = sdf.format(now);
        this.endHour = this.startHour;
    }

    public void addAccount(String id) {
        this.account = accountService.get(id);
    }

    public void addSportFacility(String id) {
        this.sportsFacility = sportsFacilityService.get(id);
    }


    public void createReservation() {
        try {
            boolean result = false;
            Date parseStartDate = new SimpleDateFormat("d MMMM, y HH:mm", Locale.US)
                    .parse(this.startDate + " " + this.startHour + ":00");
            Date parseEndDate = new SimpleDateFormat("d MMMM, y HH:mm", Locale.US)
                    .parse(this.endDate + " " + this.endHour + ":00");

            Reservation reservation = new Reservation(
                    UUID.randomUUID().toString(),
                    account,
                    sportsFacility,
                    parseStartDate,
                    parseEndDate,
                    true
            );

            if (reservation.getAccount() != null && reservation.getFacility() != null) {
                result = this.reservationService.add(reservation);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getReturnValue() {
        return "/showReservations";
    }

    //<editor-fold desc="getters and setter">

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    //</editor-fold>
}
