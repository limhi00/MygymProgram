package com.mygym.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygym.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name);

}
