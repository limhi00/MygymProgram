package com.mygym;
 


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mygym.domain.Category;
import com.mygym.domain.Report;
import com.mygym.persistence.BoardRepository;
import com.mygym.persistence.CategoryRepository;
import com.mygym.persistence.ReportRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerTest {
	
	@Autowired 
	private BoardRepository boardRepo;
	@Autowired
	private CategoryRepository cateRepo;
	@Autowired
	private ReportRepository repRepo;
	
	@Test
	public void boardInsertTest() {
		Category cate1 = new Category();
		cate1.setName("공지사항");
		cateRepo.save(cate1);
		
		Category cate2 = new Category();
		cate2.setName("자유게시판");
		cateRepo.save(cate2);
		
		Category cate3 = new Category();
		cate3.setName("운동영상");
		cateRepo.save(cate3);
		
		Category cate4 = new Category();
		cate4.setName("챌린지");
		cateRepo.save(cate4);
	}
	
	@Test
	@Ignore
	public void reportInsertTest() {
		for(int i=1; i<=20; i++) {
			Report report = new Report();
			report.setRTitle("신고제목" + i);
			report.setRContent(i + "번째 신고내용입니다");
			repRepo.save(report);
		}
	}

}
