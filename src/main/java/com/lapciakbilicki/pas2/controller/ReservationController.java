package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.ReservationService;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestScoped
@Named
public class ReservationController implements Serializable {
    private List<Reservation> reservations;

    private String startDate,
            endDate,
            facilityName,
            accountLogin,
            startHour, endHour,
            message, sort,
            priceFromFacilityFilter = "0",
            priceToFacilityFilter = "0",
            nameFacilityFilter = "",
            nameAccountFilter = "";

    @Inject
    private ReservationService reservationService;
    @Inject
    private AccountService accountService;
    @Inject
    private SportsFacilityService sportsFacilityService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;




    @Inject
    private HttpServletRequest request;

    @PostConstruct
    public void init() {
        String userName = request.getParameter("username");

        reservations = new ArrayList<>(reservationService.getUserReservations(userName));
//        reservations = new ArrayList<>(reservationService.getAll());

        if (requestMap.containsKey("sort")) {
            this.sort = this.requestMap.get("sort");
            reservations = this.reservationService.filterReservations(this.requestMap.get("sort"));
        }
    }

    public List<Reservation> getAll() {
        return reservations;
    }

    public List<Account> getAllAccounts() {
        return filterAccount();
    }

    public List<SportsFacility> getAllSportsFacilities() {
        return filterFacility();
    }

    public void deleteReservation(String id) {
        this.reservationService.deleteReservation(id);
        this.init();
    }

    public List<Account> filterAccount() {
        return accountService.filterAccount(nameAccountFilter);
    }

    public List<SportsFacility> filterFacility() {
        return this.sportsFacilityService.filterFacility(this.priceFromFacilityFilter, this.priceToFacilityFilter, this.nameFacilityFilter);
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNameAccountFilter() {
        return nameAccountFilter;
    }

    public void setNameAccountFilter(String nameAccountFilter) {
        this.nameAccountFilter = nameAccountFilter;
    }

    public String getPriceFromFacilityFilter() {
        return priceFromFacilityFilter;
    }

    public void setPriceFromFacilityFilter(String priceFromFacilityFilter) {
        this.priceFromFacilityFilter = priceFromFacilityFilter;
    }

    public String getPriceToFacilityFilter() {
        return priceToFacilityFilter;
    }

    public void setPriceToFacilityFilter(String priceToFacilityFilter) {
        this.priceToFacilityFilter = priceToFacilityFilter;
    }

    public String getNameFacilityFilter() {
        return nameFacilityFilter;
    }

    public void setNameFacilityFilter(String nameFacilityFilter) {
        this.nameFacilityFilter = nameFacilityFilter;
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
