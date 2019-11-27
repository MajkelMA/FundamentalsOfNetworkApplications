package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.ReservationService;
import com.lapciakbilicki.pas2.service.SportsFacilityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
@ViewScoped
@Named
public class ReservationController implements Serializable {
    private List<Reservation> reservations;

    private String startDate, endDate, facilityName, accountLogin, startHour, endHour, message;

    @Inject
    private ReservationService reservationService;
    @Inject
    private AccountService accountService;
    @Inject
    private SportsFacilityService sportsFacilityService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        reservations = new ArrayList<>(reservationService.getAll());
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, y", Locale.US);
        this.startDate = sdf.format(now);
        this.endDate = this.startDate;
        sdf = new SimpleDateFormat("H");
        this.startHour = sdf.format(now);
        this.endHour = this.startHour;
    }

    public List<Reservation> getAll() {
        return reservations;
    }

    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    public List<SportsFacility> getAllSportsFacilities() {
        return sportsFacilityService.getAll();
    }

    public void deleteReservation(String id) {
        Reservation reservation = reservations.stream()
                .filter(res -> res
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
        reservationService.remove(reservation);
        this.init();
    }

    public void changeReservationActive(String id) {
        reservationService.reservationDeactivation(id);
    }

    //<editor-fold desc="getters and setter">
    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFacilityName() {
        return this.facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAccountLogin() {
        return this.accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return this.endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //</editor-fold>
}
