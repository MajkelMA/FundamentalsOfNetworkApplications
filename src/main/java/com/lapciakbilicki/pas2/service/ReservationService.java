package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.repository.ReservationRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class ReservationService extends ServiceAdapter<Reservation> {

    public ReservationService() {

    }

    @PostConstruct
    @Inject
    public void init(ReservationRepository reservationRepository) {
        this.repository = reservationRepository;
    }

    public void reservationDeactivation(String id) {
        Reservation reservation = this.get(id);
        if (reservation != null) {
            Date now = Calendar.getInstance().getTime();
            if (reservation.getStartDate().before(now)) {
                reservation.setActive(false);
                reservation.setFinishDate(now);
            }
        }
    }

    public List<Reservation> filterReservations(String argument) {
        if (argument.equals("Active")) {
            return this.repository.getAll().stream().filter(Reservation::isActive).collect(Collectors.toList());
        } else if (argument.equals("Inactive")) {
            return this.repository.getAll().stream().filter(res -> !res.isActive()).collect(Collectors.toList());
        } else {
            return this.repository.getAll();
        }
    }

    public List<Reservation> getUserReservations(String login){
        return this.repository.getAll().stream()
                .filter(reservation -> reservation.getAccount().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    public void deleteReservation(String id) {
        Reservation reservation = this.repository.getAll().stream()
                .filter(res -> res
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
        if (reservation.isActive())
            this.repository.remove(reservation);
    }
}
