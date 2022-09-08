package com.mygym.persistence;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mygym.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	// MemberDetailService에서 사용자 조회에 사용할 메서드
	Optional<Member> findByUsername(String username);
	// 이름과 이메일을 조건으로 회원 아이디 찾기
	Member findByNameAndEmail(String name, String email);
	// 아이디와 이메일을 조건으로 회원 비밀번호 찾기
	Member findByUsernameAndEmail(String username, String email);

	@Query(value = "SELECT * FROM member WHERE role = ?1", nativeQuery = true)
	List<Member> getRoleList(String role);
	
	@Query(value = "SELECT * FROM member WHERE role = ?1", nativeQuery = true)
	Page<Member> getRoleList(String role, Pageable pageable);

	@Query(value = "SELECT * FROM member WHERE role = ?1 AND name LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Member> getByNameContaining(String role, String SearchKeyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM member WHERE role = ?1 AND phone LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Member> getByPhoneContaining(String role, String SearchKeyword, Pageable pageable);
}