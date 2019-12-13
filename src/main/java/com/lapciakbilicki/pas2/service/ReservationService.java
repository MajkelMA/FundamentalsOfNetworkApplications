package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.repository.ReservationRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class ReservationService extends ServiceAdapter<Reservation> {

    @Inject
    private FacesContext facesContext;

    @Inject
    private AccountService accountService;

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
        facesContext = FacesContext.getCurrentInstance();
        String login = facesContext.getExternalContext().getRemoteUser();

        if (accountService.getAccountByLogin(login)
                .getRoles()
                .stream()
                .filter(role -> role.getName().equals("Resources_Admin"))
                .findAny()
                .orElse(null) == null) {
            switch (argument) {
                case "Active":
                    return this.repository
                            .getAll()
                            .stream()
                            .filter(Reservation::isActive)
                            .filter(reservation -> reservation.getAccount().getLogin().equals(login))
                            .collect(Collectors.toList());
                case "Inactive":
                    return this.repository
                            .getAll()
                            .stream()
                            .filter(res -> !res.isActive())
                            .filter(reservation -> reservation.getAccount().getLogin().equals(login))
                            .collect(Collectors.toList());
                default:
                    return this.repository.getAll().stream()
                            .filter(reservation -> reservation.getAccount().getLogin().equals(login))
                            .collect(Collectors.toList());
            }
        } else {
            switch (argument) {
                case "Active":
                    return this.repository
                            .getAll()
                            .stream()
                            .filter(Reservation::isActive)
                            .collect(Collectors.toList());
                case "Inactive":
                    return this.repository
                            .getAll()
                            .stream()
                            .filter(res -> !res.isActive())
                            .collect(Collectors.toList());
                default:
                    return new ArrayList<>(this.repository.getAll());
            }
        }
    }

    public List<Reservation> getUserReservations() {
        facesContext = FacesContext.getCurrentInstance();
        String login = facesContext.getExternalContext().getRemoteUser();

        if (accountService.getAccountByLogin(login)
                .getRoles()
                .stream()
                .filter(role -> role.getName().equals("Resources_Admin"))
                .findAny()
                .orElse(null) != null) {
            return this.repository.getAll();
        } else {
            return this.repository.getAll().stream()
                    .filter(reservation -> reservation.getAccount().getLogin().equals(login))
                    .collect(Collectors.toList());
        }
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
