package com.mygym.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "reservation")
@Entity
public class ClassDiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_ID")
	private Long cdseq;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) 
	private Member member;
	
	@OneToOne
	@JoinColumn(name="RES_ID", nullable=false, updatable=false)
	private Reservation reservation;
	
	private String content;
}
