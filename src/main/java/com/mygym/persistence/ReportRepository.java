package com.mygym.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.mygym.domain.Report;


public interface ReportRepository extends CrudRepository<Report, Long> {

	Page<Report> findAll(Pageable pageable);
}
