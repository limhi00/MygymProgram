package com.mygym.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Report {
	
	@Id
	@GeneratedValue
	private Long rseq;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_SEQ" , nullable=false)
	private Board board;
	
//	@ManyToOne
//	@JoinColumn(name = "mid" , nullable=false)
//	private Member member;
	
	private String rTitle;
	private String rContent;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date rIndate = new Date();
}