package com.mygym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mygym.domain.Member;
import com.mygym.domain.Role;

public interface MemberService {
	
	Member getMember(String username);
	
	void deleteMember(String username);
	
	Member joinMember(String username, String name, String email, String password, String phone, Role role);
	
	void modifyMemberInfo(Member member);
	
	// 회원 아이디 조회
	Member getUsername(Member member, String username);
	
	/* 아이디, 비밀번호 찾기*/
	Member doFindId(String name, String email);
	Member doFindPwd(String username, String email);

	Page<Member> getRoleList(String role, Pageable pageable);
	
	Page<Member> getSearchNameMemberList(String role, String searchKeyword, Pageable pageable);
	
	Page<Member> getSearchPhoneMemberList(String role, String searchKeyword, Pageable pageable);
}
