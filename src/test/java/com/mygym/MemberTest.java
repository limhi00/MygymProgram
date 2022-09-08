package com.mygym;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mygym.domain.Member;
import com.mygym.domain.Role;
import com.mygym.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	//@Ignore
	public void 시큐리티_회원가입() {
		
//			Member member = new Member();
//			member.setUsername("ADMIN");
//			member.setName("관리자");
//			member.setEmail("ADMIN@email.com");
//			member.setPhone("01012349999");
//			member.setPassword(passwordEncoder.encode("123"));
//			member.setRole(Role.ROLE_ADMIN);
//			memberRepo.save(member);
		for(int i=1; i<=20; i++) {
			Member member2 = new Member();
			member2.setUsername("TRAINER"+i);
			member2.setName("트레이너"+i);
			member2.setEmail("TRAINER@email.com");
			member2.setPhone("010123488"+i);
			member2.setPassword(passwordEncoder.encode("123"));
			member2.setRole(Role.ROLE_TRAINER);
			memberRepo.save(member2);
			
			Member member3 = new Member();
			member3.setUsername("MEMBER"+i);
			member3.setName("일반회원"+i);
			member3.setEmail("MEMBER@email.com");
			member3.setPhone("010123411"+i);
			member3.setPassword(passwordEncoder.encode("123"));
			member3.setRole(Role.ROLE_MEMBER);
			memberRepo.save(member3);
		}
	}
	
	@Test
	@Ignore
	public void 시큐리티_트레이너_회원가입() {
		Member member = new Member();
		member.setUsername("TRAINER");
		member.setName("트레이너");
		member.setEmail("TRAINER@email.com");
		member.setPhone("01012348888");
		member.setPassword(passwordEncoder.encode("123"));
		member.setRole(Role.ROLE_TRAINER);
		memberRepo.save(member);
	}
	
	@Test
	@Ignore
	public void 시큐리티_멤버_회원가입() {
		Member member = new Member();
		member.setUsername("MEMBER");
		member.setName("일반회원");
		member.setEmail("MEMBER@email.com");
		member.setPhone("01012341111");
		member.setPassword(passwordEncoder.encode("123"));
		member.setRole(Role.ROLE_MEMBER);
		memberRepo.save(member);
	}
		
		@Test
		@Ignore
		public void 전체회원삭제() {
			memberRepo.deleteAll();
		}
}
