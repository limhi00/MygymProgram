package com.mygym.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mygym.domain.Member;
import com.mygym.domain.Role;
import com.mygym.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepo;
	private final PasswordEncoder passwordEncoder;
	
	public Page<Member> getRoleList(String role, Pageable pageable) {
		
		return memberRepo.getRoleList(role, pageable);
	}
	
	public Page<Member> getSearchNameMemberList(String role, String searchKeyword, Pageable pageable) {
		
		return memberRepo.getByNameContaining(role, searchKeyword, pageable);
	}
	
	public Page<Member> getSearchPhoneMemberList(String role, String searchKeyword, Pageable pageable) {
		
		return memberRepo.getByPhoneContaining(role, searchKeyword, pageable);
	}
	
	@Override // 회원가입
	public Member createForm(String username, String name, String email, String password, String phone) {
		if(memberRepo.findById(username).isPresent()) {
			throw new IllegalStateException("중복된 회원 ID가 존재합니다.");
		} else {
			Member member = new Member();
			member.setUsername(username);
			member.setEmail(email);
			member.setName(name);
			member.setPassword(passwordEncoder.encode(password));
			member.setPhone(phone);
			member.setRole(Role.ROLE_MEMBER);
			//member.setRole(null);
			//member.getRole();
			memberRepo.save(member);
		}
		return null;
	}
	
	@Override // 아이디를 조건으로 회원 검색
	public Member getMember(String username) {
		Optional<Member> findMember = memberRepo.findByUsername(username);
		if (findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}
	
	@Override // 마이페이지 회원정보 수정
	public void modifyMemberInfo(Member member) {
		String encodePassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodePassword);
		member.setRole(Role.ROLE_MEMBER);
		memberRepo.save(member);
	}
	
	@Override // 마이페이지 회원탈퇴
	public void deleteMember(String username) {
		memberRepo.deleteById(username);
	}
	
}