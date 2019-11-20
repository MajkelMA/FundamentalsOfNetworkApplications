package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.ReservationService;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@ViewScoped
public class CreateReservationController implements Serializable {
    private String startDate, endDate, facilityName, accountLogin, startHour, endHour, message;

    @Inject
    private ReservationService reservationService;

    @Inject
    private AccountController accountController;

    @Inject
    private SportsFacilityController sportsFacilityController;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, y", Locale.US);
        startDate = sdf.format(now);
        endDate = startDate;
        sdf = new SimpleDateFormat("H");
        startHour = sdf.format(now);
        endHour = startHour;
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
                    accountController.getAll()
                            .stream()
                            .filter(account -> account
                                    .getLogin()
                                    .equals(accountLogin))
                            .findFirst()
                            .orElse(null),
                    sportsFacilityController.getAll()
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

    //<editor-fold desc="getters">
    public String getMessage() {
        return message;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getAccountLogin() {
        return accountLogin;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    //</editor-fold>

    //<editor-fold desc="setters">
    public void setMessage(String message) {
        this.message = message;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    //</editor-fold>
}
