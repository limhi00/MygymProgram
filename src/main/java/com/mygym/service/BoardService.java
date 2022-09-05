package com.mygym.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.mygym.domain.Board;
import com.mygym.domain.Category;
import com.mygym.domain.Report;

public interface BoardService {

	/* board */
	
//	List<Board> getBoardList(Board board);
	
	Page<Board> getBoardList(Pageable pageable);
	
	Page<Board> getBoardSearchTitleList(String searchKeyword, Pageable pageable);
	
	Page<Board> getBoardSearchContList(String searchKeyword, Pageable pageable);
	
	Page<Board> getCategoryBoardList(Long searchCategory, Pageable pageable);
	
	Page<Board> getCategoryBoardSearchTitleList(Long searchCategory, String searchKeyword, Pageable pageable);
	
	Page<Board> getCategoryBoardSearchContList(Long searchCategory, String searchKeyword, Pageable pageable);

	Board getBoard(Board board);
	
	void writeBoard(Board board, MultipartFile file) throws Exception;

	void deleteBoard(Board board);
	
	/* category */
	
	List<Category> getCategoryList(Category category);
	
	Category getCategory(Category category);
	
	void insertCategory(Category category);
	
	void updateCategory(Category category);
	
	void deleteCategory(Category category);
	
	/* report */
	
	Page<Report> getReportList(Pageable pageable);
	
	Report getReport(Report report);

	void insertReport(Report report);
	
	void updateReport(Report report);
	
	void deleteReport(Report report);
}