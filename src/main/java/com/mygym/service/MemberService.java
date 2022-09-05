package com.mygym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mygym.domain.Member;

public interface MemberService {

//	List<Member> getMemberList();
	
	Page<Member> getRoleList(String role, Pageable pageable);
	
	Page<Member> getSearchNameMemberList(String role, String searchKeyword, Pageable pageable);
	
	Page<Member> getSearchPhoneMemberList(String role, String searchKeyword, Pageable pageable);
	
	Member createForm(String name, String username, String email, String password, String phone);
	Member getMember(String username); // 아이디를 조건으로 회원 검색
	void modifyMemberInfo(Member member); // 회원 수정
	void deleteMember(String username); // 회원 탈퇴	

}