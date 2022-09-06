package com.mygym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygym.domain.ClassDiary;
import com.mygym.domain.Member;
import com.mygym.domain.Reservation;
import com.mygym.persistence.ClassDiaryRepository;
import com.mygym.persistence.MemberRepository;
import com.mygym.persistence.ReservationRepository;
@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ClassDiaryRepository cdRepo;

	@Override
	public List<Reservation> getReservationList(String username) {

		return resRepo.getReservationList(username);
	}
	
	public List<Member> getCTrainerList(String role) {
		
		role = "ROLE_TRAINER";
		
		return memberRepo.getRoleList(role);
	}

	@Override
	public Reservation getReservation(Long rseq) {
		
		return resRepo.findById(rseq).get();
	}

	@Override
	public String getCTrainerName(String cTrainer) {
		
		return resRepo.getCTrainerName(cTrainer);
	}

	@Override
	public Long insertReservation(Reservation res) {
		
		res.setClassDate(res.getClassDate());
		
		Long rseq = resRepo.save(res).getRseq();

		ClassDiary cDiary = new ClassDiary();
		cDiary.setReservation(res);
		cDiary.setMember(res.getMember());
		cdRepo.save(cDiary);
		
		return rseq;
	}

	@Override
	public void deleteReservation(Reservation res) {
		
		resRepo.deleteById(res.getRseq());
	}

	@Override
	public ClassDiary getClassDiary(Long rseq) {
		
		return cdRepo.getClassDiary(rseq);
	}
	
	public ClassDiary findClassDiary(Reservation res) {
		
		return cdRepo.findByReservation(res);
	}
	
	public ClassDiary getClassDiaryCdseq(Long cdseq) {
		
		return cdRepo.findById(cdseq).get();
	}

	@Override
	public void insertClassDiary(ClassDiary cDiary) {
		
		cdRepo.save(cDiary);
	}

	@Override
	public List<Reservation> getTrainerReservationList(String username) {
		
		return resRepo.getTrainerReservationList(username);
	}
	
}
