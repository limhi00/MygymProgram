package com.mygym.service;

import java.util.List;

import com.mygym.domain.DietDiary;

public interface DietDiaryService {

	List<DietDiary> getDiaryList(String username);
	
	DietDiary getDiary(Long dseq);
	
	Long insertDiary(DietDiary ddiary);

	void updateDiary(DietDiary ddiary);
}
