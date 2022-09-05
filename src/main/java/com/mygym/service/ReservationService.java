package com.mygym.service;


import java.util.List;

import com.mygym.domain.Reservation;

public interface ReservationService {
	
	List<Reservation> getReservationList(String username);
	
	Reservation getReservation(Long rseq);
	
	Long insertReservation(Reservation res);
	
	void deleteReservation(Reservation res);

}
