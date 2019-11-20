package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.model.reservation.Reservation;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class ReservationRepository extends RepositoryAdapter<Reservation> {

    public ReservationRepository() { this.setListOfItems(new ArrayList<>());}

    @Override
    public void update(Reservation item) {
        Reservation reservation = this.get(item.getId());
        reservation.setAccount(item.getAccount());
        reservation.setFacility(item.getFacility());
        reservation.setStartDate(item.getStartDate());
        reservation.setEndDate(item.getEndDate());
    }
}
