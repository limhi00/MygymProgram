package com.mygym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygym.domain.DietDiary;
import com.mygym.persistence.DietDiaryRepository;

@Service
public class DietDiaryServiceImpl implements DietDiaryService {
	
	@Autowired
	private DietDiaryRepository dietRepo;
	
	public List<DietDiary> getDiaryList(String username) {
		
		return dietRepo.getDietDiaryList(username);
	}
	
	public DietDiary getDiary(Long dseq) {
		
		return dietRepo.findById(dseq).get();
	}
	
	public Long insertDiary(DietDiary ddiary) {
		
		ddiary.setD_indate(ddiary.getD_indate());
		Long dseq = dietRepo.save(ddiary).getDseq();
		
		return dseq;
	}
	
	public void updateDiary(DietDiary ddiary) {
		
		dietRepo.save(ddiary);
	}
	
//	@Override
//	public long insertDietDiary(DietDiary ddiary) {
//		ddiary.setBreakfast(ddiary.getBreakfast());
//		ddiary.setDinner(ddiary.getDinner());
//		ddiary.setFat(ddiary.getFat());
//		ddiary.setLunch(ddiary.getLunch());
//		ddiary.setMuscle(ddiary.getMuscle());
//		ddiary.setSnack(ddiary.getSnack());
//		ddiary.setWeight(ddiary.getWeight());
//
//		ddiary.setD_indate(ddiary.getD_indate());
//		
//		long dseq = dietrepo.save(ddiary).getDseq();
//		
//		return dseq;
//	}
//	
//	@Override
//	public DietDiary getDietDiary(DietDiary ddiary) {
//		
//		return dietrepo.findById(ddiary.getDseq()).get();
//	}

}
