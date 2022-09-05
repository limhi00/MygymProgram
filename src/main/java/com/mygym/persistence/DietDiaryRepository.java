package com.mygym.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mygym.domain.DietDiary;


public interface DietDiaryRepository extends CrudRepository<DietDiary, Long>{
	
	@Query(value = "SELECT * FROM diet_diary WHERE member_id = ?1", nativeQuery = true)
	public List<DietDiary> getDietDiaryList(String username);
}
