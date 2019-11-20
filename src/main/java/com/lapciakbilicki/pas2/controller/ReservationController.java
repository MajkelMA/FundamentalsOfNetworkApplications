package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.service.ReservationService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class ReservationController implements Serializable {
    private List<Reservation> reservations;

    @Inject
    private ReservationService reservationService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        reservations = new ArrayList<>(reservationService.getAll());
    }

    public List<Reservation> getAll() {
        return reservations;
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
}
