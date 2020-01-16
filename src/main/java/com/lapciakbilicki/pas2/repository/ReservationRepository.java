package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.model.reservation.Reservation;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
public class ReservationRepository extends RepositoryAdapter<Reservation> implements Serializable {

    public ReservationRepository() {
        this.setListOfItems(Collections.synchronizedList(new ArrayList<>()));
    }

    @Override
    public boolean update(Reservation item) {
        Reservation reservation = this.get(item.getId());
        reservation.setAccount(item.getAccount());
        reservation.setFacility(item.getFacility());
        reservation.setStartDate(item.getStartDate());
        reservation.setEndDate(item.getEndDate());
        return false;
    }
}
