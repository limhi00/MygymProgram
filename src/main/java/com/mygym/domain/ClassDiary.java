package com.mygym.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ClassDiary {
	@Id
	@GeneratedValue
	private Long cdseq;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) 
	private Member member;
	
	@OneToOne
	@JoinColumn(name="RES_ID", nullable=false, updatable=false)
	private Reservation res;
	
	private String content;
}
