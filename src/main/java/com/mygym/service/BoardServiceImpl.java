package com.mygym.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mygym.domain.Board;
import com.mygym.domain.Category;
import com.mygym.domain.Report;
import com.mygym.persistence.BoardRepository;
import com.mygym.persistence.CategoryRepository;
import com.mygym.persistence.ReportRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private ReportRepository repRepo;
	
	@Override
//	public List<Board> getBoardList(Board board) {
//		
//		return boardRepo.findAll();
//		//return (List<Board>) boardRepo.findAll();
//	}
	
	public Page<Board> getBoardList(Pageable pageable) {
		
		return boardRepo.findAll(pageable);
	}
	
	public Page<Board> getBoardSearchTitleList(String searchKeyword, Pageable pageable) {
		
		return boardRepo.findByTitleContaining(searchKeyword, pageable);
	}
	
	public Page<Board> getBoardSearchContList(String searchKeyword, Pageable pageable) {
		
		return boardRepo.findByContentContaining(searchKeyword, pageable);
	}
	
	public Page<Board> getCategoryBoardList(Long searchCategory, Pageable pageable) {
		
		return boardRepo.getCategoryBoardList(searchCategory, pageable);
	}
	
	public Page<Board> getCategoryBoardSearchTitleList(Long searchCategory, String searchKeyword, Pageable pageable) {
		
		return boardRepo.getCategoryBoardSearchTitleList(searchCategory, searchKeyword, pageable);
	}
	
	public Page<Board> getCategoryBoardSearchContList(Long searchCategory, String searchKeyword, Pageable pageable) {
		
		return boardRepo.getCategoryBoardSearchContentList(searchCategory, searchKeyword, pageable);
	}
	
	@Override
	public Board getBoard(Board board) {
	
		return boardRepo.findById(board.getBseq()).get();
	}
	
	@Override
	public void writeBoard(Board board, MultipartFile file) throws Exception {
		
		if(file.isEmpty()) {
			board.setFilename("");
		} else {
			String projectPath = "C:/fileUpload/images/";
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			
			File saveFile = new File(projectPath, fileName);
				
			file.transferTo(saveFile);
			
			board.setFilename(fileName);
			board.setFilepath("/files/" + fileName);
		}
		
		boardRepo.save(board);
	}

	@Override
	public void deleteBoard(Board board) {
		
		boardRepo.deleteById(board.getBseq());
	}
	
	public List<Category> getCategoryList(Category category) {
		
		return cateRepo.findAll();
	}
	
	public Category getCategory(Category category) {
		
		System.out.println("category=" + category);
		return cateRepo.findById(category.getCid()).get();
	}
	
	public void insertCategory(Category category) {
		
		cateRepo.save(category);
	}
	
	public void updateCategory(Category category) {
		
		cateRepo.save(category);
	}
	
	public void deleteCategory(Category category) {
		
		cateRepo.deleteById(category.getCid());
	}
	
	public Page<Report> getReportList(Pageable pageable) {
		
		return repRepo.findAll(pageable);
	}
	
	public Report getReport(Report report) {
		
		return repRepo.findById(report.getRseq()).get();
	}
	
	public void insertReport(Report report) {
		
		repRepo.save(report);
	}
	
	public void updateReport(Report report) {
		
		repRepo.save(report);
	}
	
	public void deleteReport(Report report) {
		
		repRepo.deleteById(report.getRseq());
	}
}
