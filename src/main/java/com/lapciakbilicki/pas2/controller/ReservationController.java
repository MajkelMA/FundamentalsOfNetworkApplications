package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.ReservationService;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
@Named
public class ReservationController implements Serializable {
    private List<Reservation> reservations;

    private String startDate, endDate, facilityName, accountLogin, startHour, endHour, message, sort, priceFacilityFilter = "", nameFacilityFilter = "";

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
        if (requestMap.containsKey("sort")) {
            this.sort = this.requestMap.get("sort");
            if (this.sort.equals("Active")) {
                reservations = reservationService.getAll().stream().filter(Reservation::isActive).collect(Collectors.toList());
            } else if (this.sort.equals("Inactive")) {
                reservations = reservationService.getAll().stream().filter(res -> !res.isActive()).collect(Collectors.toList());
            } else {
                reservations = new ArrayList<>(reservationService.getAll());
            }
        }
    }

    public int update() {
        return 1;
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
        if (reservation.isActive())
            reservationService.remove(reservation);
        this.init();
    }

    public String dateToStr(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("d/M/y HH:mm", Locale.US);
            return sdf.format(date);
        }
        return "-";
    }

    public void changeReservationActive(String id) {
        reservationService.reservationDeactivation(id);
    }

    //<editor-fold desc="getters and setter">

    public String getPriceFacilityFilter() {
        return priceFacilityFilter;
    }

    public void setPriceFacilityFilter(String priceFacilityFilter) {
        this.priceFacilityFilter = priceFacilityFilter;
    }

    public String getNameFacilityFilter() {
        return nameFacilityFilter;
    }

    public void setNameFacilityFilter(String nameFacilityFilter) {
        this.nameFacilityFilter = nameFacilityFilter;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

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
