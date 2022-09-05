package com.mygym.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	@Column(name = "RES_ID")
	private Long rseq;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) 
	private Member member;
	
	private String classDate;
	
}
