package com.mygym.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="resList")
@Entity
public class Member {

	@Id
	@Column(name = "MEMBER_ID",length = 15)
	private String username; // 회원가입, 로그인에 사용될 ID
	
	@Column(nullable = false, length = 30)
	private String name;	
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable = false, unique = true, length = 50)
	private String phone;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createDate; 
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Reservation> resList = new ArrayList<Reservation>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<DietDiary> dietDiaryList = new ArrayList<DietDiary>();
}
