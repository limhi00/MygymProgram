package com.mygym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygym.domain.Reservation;
import com.mygym.persistence.ClassDiaryRepository;
import com.mygym.persistence.ReservationRepository;
@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private ClassDiaryRepository cdRepo;

	@Override
	public List<Reservation> getReservationList(String username) {

		return resRepo.getReservationList(username);
	}

	@Override
	public Reservation getReservation(Long rseq) {
		
		return resRepo.findById(rseq).get();
	}

	@Override
	public Long insertReservation(Reservation res) {
		
		res.setClassDate(res.getClassDate());
		Long rseq = resRepo.save(res).getRseq();
		
		return rseq;
	}

	@Override
	public void deleteReservation(Reservation res) {
		
		resRepo.deleteById(res.getRseq());
	}
	
}
