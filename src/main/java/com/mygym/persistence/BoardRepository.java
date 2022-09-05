package com.mygym.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mygym.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
	
	Page<Board> findByContentContaining(String searchKeyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM board WHERE category_id = ?1", nativeQuery = true)
	Page<Board> getCategoryBoardList(Long searchCategory, Pageable pageable);
	
	@Query(value = "SELECT * FROM board WHERE category_id = ?1 AND title LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Board> getCategoryBoardSearchTitleList(Long searchCategory, String searchKeyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM board WHERE category_id = ?1 AND content LIKE '%'||?2||'%'", nativeQuery = true)
	Page<Board> getCategoryBoardSearchContentList(Long searchCategory, String searchKeyword, Pageable pageable);
}
