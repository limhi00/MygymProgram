package com.mygym.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mygym.domain.ClassDiary;
import com.mygym.domain.Reservation;

public interface ClassDiaryRepository extends CrudRepository<ClassDiary, Long> {

	@Query(value = "SELECT c FROM ClassDiary c WHERE c.reservation.rseq = ?1")
	public ClassDiary getClassDiary(Long rseq);
	
	public ClassDiary findByReservation(Reservation res);
}
