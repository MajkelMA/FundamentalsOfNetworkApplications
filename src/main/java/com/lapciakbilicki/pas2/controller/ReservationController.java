package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.ReservationService;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        startDate = sdf.format(now);
        endDate = startDate;
        sdf = new SimpleDateFormat("H");
        startHour = sdf.format(now);
        endHour = startHour;
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

    public void createReservation() {
        Date startDate = null, endDate = null;

        try {
            startDate = new SimpleDateFormat("d MMMM, y HH:mm", Locale.US).parse(this.startDate + " " + this.startHour + ":00");
            endDate = new SimpleDateFormat("d MMMM, y HH:mm", Locale.US).parse(this.endDate + " " + this.endHour + ":00");
        } catch (ParseException e) {
            message = " exception: " + e.getMessage();
        }

        boolean result = false;
        if (endDate.after(startDate)) {
            Reservation reservation = new Reservation(UUID.randomUUID().toString(),
                    accountService.getAll()
                            .stream()
                            .filter(account -> account
                                    .getLogin()
                                    .equals(accountLogin))
                            .findFirst()
                            .orElse(null),
                    sportsFacilityService.getAll()
                            .stream()
                            .filter(facility -> facility
                                    .getName()
                                    .equals(facilityName))
                            .findFirst()
                            .orElse(null),
                    startDate,
                    endDate);
            if (reservation.getAccount() != null && reservation.getFacility() != null) {
                result = this.reservationService.add(reservation);
            }
        }
        if (result) {
            message = "Reservation created successfully";
        } else {
            message = "Reservation did not create";
        }
    }

    //<editor-fold desc="getters and setter">

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //</editor-fold>
}
