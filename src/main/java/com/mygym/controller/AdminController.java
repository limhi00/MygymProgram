package com.mygym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygym.domain.Board;
import com.mygym.domain.Category;
import com.mygym.domain.Member;
import com.mygym.domain.Report;
import com.mygym.service.BoardService;
import com.mygym.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;
	
	// 카테고리 목록
	@GetMapping("/categoryList")
	public String categoryList(Model model, Category category) {
		List<Category> categoryList = boardService.getCategoryList(category);
//		for (Category c : categoryList) {
//			System.out.println(c);
//		}
		model.addAttribute("categoryList", categoryList);
		
		return "admin/categoryList";
	}

	// 카테고리 등록 뷰
	@RequestMapping("/insertCategoryView")
	public String insertCategoryView() {
		
		return "admin/insertCategory";
	}
	
	// 카테고리 등록
	@PostMapping("/insertCategory")
	public String insertCategory(Category category) {
		
		boardService.insertCategory(category);
		
		return "admin/insertCateResult";
	}
	
	// 카테고리 삭제 뷰
	@RequestMapping("/deleteCategoryView")
	public String deleteCategoryView(Model model, Category category) {
		
		model.addAttribute("category", boardService.getCategory(category));
		
		return "admin/deleteCategory";
	}
	
	// 카테고리 삭제
	@RequestMapping("/deleteCategory")
	public String deleteCategory(Category category) {
		
		boardService.deleteCategory(category);
			
		return "admin/deleteCateResult";
	}

	// 신고글 목록
	@RequestMapping("/reportBoardList")
	public String reportBoardList(Model model, @PageableDefault(page=0, size=10, sort="rseq", direction=Sort.Direction.DESC) Pageable pageable) {
		
		Page<Report> reportList = boardService.getReportList(pageable);
		
		int nowPage = reportList.getPageable().getPageNumber() +1 ;
		int startPage = Math.max(nowPage - 9, 1);
		int endPage = Math.min(nowPage + 10, reportList.getTotalPages());
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "admin/reportBoardList";
	}
	
	@RequestMapping("/reportView")
	public String reportView(Model model, Report report) {
		
		model.addAttribute("report", boardService.getReport(report));
		
		return "admin/reportView";
	}
	
	@RequestMapping("/deleteBoardAdmin")
	public String deleteBoardAdmin(Board board) {
		
		boardService.deleteBoard(board);
		
		return "redirect:/admin/reportBoardList";
	}

	// 트레이너 관리
	@GetMapping("/trainerManagement")
	public String trainerManagementView(Model model, String role, 
			@PageableDefault(page=0, size=20, sort="name", direction=Sort.Direction.DESC) Pageable pageable,
			   String searchSelect, String searchKeyword) {
		
		Page<Member> memberList = null;
		role = "ROLE_TRAINER";
		
		if(searchSelect == null) {
			memberList = memberService.getRoleList(role, pageable);
		} else {
			if(searchSelect.equals("name")) {
				if(searchKeyword == null) {
					memberList = memberService.getRoleList(role, pageable);
				} else {
					memberList = memberService.getSearchNameMemberList(role, searchKeyword, pageable);
				}
			} else if(searchSelect.equals("phone")) {
				if(searchKeyword == null) {
					memberList = memberService.getRoleList(role, pageable);
				} else {
					memberList = memberService.getSearchPhoneMemberList(role, searchKeyword, pageable);
				}
			} else {
				memberList = memberService.getRoleList(role, pageable);
			}
		}
		int nowPage = memberList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 9, 1);
		int endPage = Math.min(nowPage +5, memberList.getTotalPages());
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "admin/trainerManagement";
	}
	
	// 회원 관리
	@GetMapping("/userManagement")
	public String userManagementView(Model model, String role,
			@PageableDefault(page=0, size=20, sort="name", direction=Sort.Direction.DESC) Pageable pageable,
			   String searchSelect, String searchKeyword) {
		
		Page<Member> memberList = null;
		role = "ROLE_MEMBER";
		
		if(searchSelect == null) {
			memberList = memberService.getRoleList(role, pageable);
		} else {
			if(searchSelect.equals("name")) {
				if(searchKeyword == null) {
					memberList = memberService.getRoleList(role, pageable);
				} else {
					memberList = memberService.getSearchNameMemberList(role, searchKeyword, pageable);
				}
			} else if(searchSelect.equals("phone")) {
				if(searchKeyword == null) {
					memberList = memberService.getRoleList(role, pageable);
				} else {
					memberList = memberService.getSearchPhoneMemberList(role, searchKeyword, pageable);
				}
			} else {
				memberList = memberService.getRoleList(role, pageable);
			}
		}
		int nowPage = memberList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 9, 1);
		int endPage = Math.min(nowPage +5, memberList.getTotalPages());
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "admin/userManagement";
	}
	
}
