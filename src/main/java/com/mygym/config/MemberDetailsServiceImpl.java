package com.mygym.config;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mygym.domain.Member;
import com.mygym.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

	private final MemberRepository memberRepo; 	
	
	@Override
	public UserDetails loadUserByUsername(String username) 
									throws UsernameNotFoundException {
		Optional<Member> optional = memberRepo.findById(username);

		if(!optional.isPresent()) {
			throw new UsernameNotFoundException("존재하지 않는" + username + "입니다.");
		} else {
			Member member = optional.get();
			return new MemberDetail(member);
		}
	}
}
