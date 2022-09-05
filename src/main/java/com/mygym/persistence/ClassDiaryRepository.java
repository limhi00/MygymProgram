package com.mygym.persistence;

import org.springframework.data.repository.CrudRepository;

import com.mygym.domain.ClassDiary;

public interface ClassDiaryRepository extends CrudRepository<ClassDiary, Long> {

}
