package com.mygym.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mygym.domain.Member;
import com.mygym.domain.MemberCreateForm;
import com.mygym.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SessionAttributes("member")
@RequestMapping("/members/")
@Controller
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("login")
	public String loginForm() {	return "members/login"; }
	
	@GetMapping("findId") // 아이디찾기
	public String findId(Model model) {
		
		return "members/findId";
	}
	
	@GetMapping("findPwd") // 아이디찾기
	public String findPwd(Model model) {
		
		return "members/findPwd";
	}
	
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete(); // 세션 만료.
		return "redirect:/";
	}
	
	// 마이페이지 폼
	@PreAuthorize("isAuthenticated() and (( #username == principal.username ) or hasRole('ROLE_ADMIN'))")
	@GetMapping("mypage/{username}")
	public String myPage(Model model, @PathVariable("username") String username) {
		Member member = memberService.getMember(username);
		model.addAttribute("member", member);
		return "members/mypage";
	}
	
    @GetMapping("join")
    public String signup(MemberCreateForm memberCreateForm) {
        return "members/join";
    }

    @PostMapping("join") 
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/join";
        }
        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordNotMatch", 
                    "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
            return "members/join";
        }
        memberService.createForm(memberCreateForm.getUsername(),
        						memberCreateForm.getName(),
        						memberCreateForm.getEmail(),
        						memberCreateForm.getPassword1(),
        						memberCreateForm.getPhone());
        
	        return "redirect:/"; 
	    }
	
    @GetMapping("mypage/delete")
    public String memberDelete(String username) {
        memberService.deleteMember(username);

        return "redirect:/";
    }
    
	// 마이페이지 리다이렉트
	@PostMapping("mypage/{username}") 
	public String myPageModify(@PathVariable("username") String username, Member member) {
		memberService.modifyMemberInfo(member);

		return "redirect:/members/mypage/{username}";
	}
}