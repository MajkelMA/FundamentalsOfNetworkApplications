package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.reservation.Reservation;
import com.lapciakbilicki.pas2.repository.ReservationRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
}
