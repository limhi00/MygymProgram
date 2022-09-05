package com.mygym.service;


import java.util.List;

import com.mygym.domain.ClassDiary;
import com.mygym.domain.Member;
import com.mygym.domain.Reservation;

public interface ReservationService {
	
	List<Reservation> getReservationList(String username);
	
	Reservation getReservation(Long rseq);
	
	Long insertReservation(Reservation res);
	
	List<Member> getCTrainerList(String role);
	
	void deleteReservation(Reservation res);
	
	ClassDiary getClassDiary(Long rseq);
	
	Long insertClassDiary(ClassDiary cDiary);

}
