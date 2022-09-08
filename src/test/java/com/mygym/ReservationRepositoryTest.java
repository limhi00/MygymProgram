package com.mygym;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mygym.domain.Reservation;
import com.mygym.persistence.ClassDiaryRepository;
import com.mygym.persistence.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {
//	@Autowired(required=true)
//	private MemberRepository memberRepo;
	
	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private ClassDiaryRepository cdRepo;
	
	private Reservation res;
	
	@Test
	public void testInsert() {
		
	}
	
	// 'yyyyMMdd'형식을 Date형식으로 변환
	public Date dateFormat(String selectedDate) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
		Date tempDate = null;
		
		try {
			tempDate = beforeFormat.parse(selectedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tempDate;
	}
}
