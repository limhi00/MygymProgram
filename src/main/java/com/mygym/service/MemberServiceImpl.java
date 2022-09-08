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

@RequiredArgsConstructor 
@Service 
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepo; 
	private final PasswordEncoder passwordEncoder;
	
	@Override // 이름과 이메일을 조건으로 회원 비밀번호 조회
	public Member doFindId(String name, String email) {
		Member findMemberUsername = memberRepo.findByNameAndEmail(name, email);
		if (findMemberUsername != null) {  
			return memberRepo.findByNameAndEmail(name, email);
		} else {
			return null;
		}
	}
	
	@Override // 아이디와 이메일을 조건으로 회원 비밀번호 조회
	public Member doFindPwd(String username, String email) {
		Member findMemberPwd = memberRepo.findByUsernameAndEmail(username, email);
		if (findMemberPwd != null) {
			return memberRepo.findByUsernameAndEmail(username, email);
		} else {
			return null;
		}
	}
	
	@Override // 회원 아이디를 조회
	public Member getUsername(Member member, String username) {
		if(memberRepo.findById(username).isPresent()) {
			return null; 
		} else {			
			memberRepo.findById(username);
		}
		return member;
	}

	@Override   
	public Member joinMember(String username, String name, String email, String password, String phone, Role role) {
		if(memberRepo.findByUsername(username).isPresent()) {
			throw new IllegalStateException("중복된 회원 ID가 존재합니다.");
		} else {
			Member member = new Member();
			member.setUsername(username);
			member.setEmail(email);
			member.setName(name);
			member.setPhone(phone);
			//String encodePassword = passwordEncoder.encode(member.getPassword());
			member.setPassword(passwordEncoder.encode(password));
			member.setRole(role);
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
		Member findMember = memberRepo.findById(member.getUsername()).get();
		findMember.setUsername(member.getUsername());
		findMember.setName(member.getName());
		findMember.setEmail(member.getEmail());
		findMember.setPhone(member.getPhone());
		String encodePassword = passwordEncoder.encode(member.getPassword());
		findMember.setPassword(encodePassword);
		findMember.getRole();
		memberRepo.save(findMember);
	}

	@Override
	public void deleteMember(String username) {
		memberRepo.deleteById(username);		
	}
	
	@Override
	public Page<Member> getRoleList(String role, Pageable pageable) {
		
		return memberRepo.getRoleList(role, pageable);
	}
	
	@Override
	public Page<Member> getSearchNameMemberList(String role, String searchKeyword, Pageable pageable) {
		
		return memberRepo.getByNameContaining(role, searchKeyword, pageable);
	}
	
	@Override
	public Page<Member> getSearchPhoneMemberList(String role, String searchKeyword, Pageable pageable) {
		
		return memberRepo.getByPhoneContaining(role, searchKeyword, pageable);
	}
}
