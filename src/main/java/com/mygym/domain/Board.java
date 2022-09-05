package com.mygym.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bseq;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String writer;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", updatable=false, nullable = false)
	private Member member;
	
	@Column(nullable = false, length = 1000)
	private String content;		
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;	// 카테고리

	@CreationTimestamp
	@Column(name="create_date", updatable = false)
	private Date createDate = new Date();
	
	@Column(nullable = false)
	private String boardPwd;
	
	private String filename;
	private String filepath;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private List<Report> reportList = new ArrayList<>();
	
	public void setCategory(Category category) {
		this.category = category;
		category.getBoardList().add(this);
	}

	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
	
}
