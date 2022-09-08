package com.mygym.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mygym.domain.Member;
import com.mygym.domain.Role;
import com.mygym.dto.MemberJoinDTO;
import com.mygym.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;

	/**
	 * 화면 흐름 순으로 정렬.
	 * 회원가입 - 로그인 - 마이페이지 - 아이디/비밀번호 찾기
	 */

    @GetMapping("/login")
	public String loginForm() {	return "members/login"; }

	 /* 회원가입 */
	 @PreAuthorize("isAnonymous")
	 @GetMapping("/join")
	 public String joinForm(MemberJoinDTO memberCreateForm) { return "members/join"; }

	@PostMapping("/join") 
    public String joinMember(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/join";
        }
        if (!memberJoinDTO.getPassword1().equals(memberJoinDTO.getPassword2())) {
            	bindingResult.rejectValue("password2", "passwordNotMatch", 
                    "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
            return "members/join";
        }   
        memberService.joinMember(memberJoinDTO.getUsername(),
					        	memberJoinDTO.getName(),
					        	memberJoinDTO.getEmail(),
					        	memberJoinDTO.getPassword1(),
					        	memberJoinDTO.getPhone(),
					        	memberJoinDTO.getRole());
	        return "redirect:/login";
	    }

		@ModelAttribute("roles")
		public Map<String, Role> roles() {
			Map<String, Role> map = new LinkedHashMap<>();
			map.put("회원", Role.ROLE_MEMBER);
			map.put("트레이너", Role.ROLE_TRAINER);
			return map; 
		}

	/* 마이페이지 폼 */
	@PreAuthorize("isAuthenticated() and (( #username == principal.username ) or hasRole('ROLE_ADMIN'))")
	@GetMapping("/mypage/{username}")
	public String myPage(Model model, @PathVariable("username") String username) {
		model.addAttribute("member", memberService.getMember(username));
		return "members/mypage";
	}
	
    @PostMapping("/mypage/{username}")
    public String myPageModify(@PathVariable("username") String username, Member member,
    							RedirectAttributes redirectAttributes) {
    	memberService.modifyMemberInfo(member);
        redirectAttributes.addAttribute("status", true);
        redirectAttributes.addFlashAttribute("msg", "비밀번호를 다시 확인해 주세요.");
    	return "redirect:/mypage/{username}";
    }

	@GetMapping("/mypage/delete")
    public String memberDelete(String username) {
        memberService.deleteMember(username);
        return "redirect:/logout";
    }

	/* 아이디찾기 */
	@GetMapping("/findIdForm") 
	public String findIdForm() { return "members/findIdForm"; }
	
	@PostMapping("/findId")
	public String findId(@RequestParam("email") String email, Member member, Model model,
						 @RequestParam(value="name", required=false) String name) {
	
		Member resultId = memberService.doFindId(name, email);
		 
		if(resultId != null) {
			model.addAttribute("result", resultId);
			model.addAttribute("msg", "회원님의 아이디는 " + resultId.getUsername() + " 입니다.");
			return "members/findId";
		} else {
			model.addAttribute("msg", "해당 이메일로 가입된 사용자가 없습니다.");
			return "members/findIdForm";
		}	
	}

	@GetMapping("/findPwdForm")
	public String findPwdForm() { return "members/findPwdForm"; }
	
	@PostMapping("/findPwd")
	public String findPwd(@RequestParam("email") String email, Member member, Model model,
						 @RequestParam(value="username", required=false) String username) {
	
		Member resultPwd = memberService.doFindPwd(username, email);
		 
		if(resultPwd != null) {
			model.addAttribute("result", resultPwd);
			model.addAttribute("msg", "회원님의 비밀번호는 " + resultPwd.getPassword() + " 입니다.");
			return "members/findPwd";
		} else {
			model.addAttribute("msg", "조회된 회원 정보가 없습니다.");
			return "members/findPwdForm";
		}	
	}
}