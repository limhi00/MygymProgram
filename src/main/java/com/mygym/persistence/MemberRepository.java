package com.mygym.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mygym.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	@Query(value = "SELECT * FROM member WHERE role = ?1", nativeQuery = true)
	Page<Member> getRoleList(String role, Pageable pageable);

	@Query(value = "SELECT * FROM member WHERE role = ?1 AND name LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Member> getByNameContaining(String role, String SearchKeyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM member WHERE role = ?1 AND phone LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Member> getByPhoneContaining(String role, String SearchKeyword, Pageable pageable);
	
	Optional<Member> findByUsername(String username);
}
