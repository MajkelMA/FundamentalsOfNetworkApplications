package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.repository.ReservationRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ReservationService extends ServiceAdapter<Reservation>{

    public ReservationService() {

    }

    @PostConstruct
    @Inject
    public void init(ReservationRepository reservationRepository) {
        this.repository = reservationRepository;
    }
}
