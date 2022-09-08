package com.mygym.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.mygym.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ToString
public class MemberJoinDTO { 
	
	@Length(min=3, max=20, message = "최소 3글자 이상 입력해 주세요.")
	@NotBlank(message = "ID는 필수 입력 항목입니다.")
	//@Pattern(regexp="^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
	private String username;
	
	@Length(min = 2, max = 10, message = "최소 2글자 이상 입력해 주세요.")
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	private String name;
	
	//@Length(min = 11, message = "'-'를 제외한 11자리를 입력해 주세요.")
	@NotBlank(message = "전화번호는 필수 입력 항목입니다.")
	//@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{3,4}")
	private String phone;
	
	@Length(min=3, max=50)
	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	private String password1;
	
	@Length(min=3, max=50)
	@NotBlank(message = "비밀번호 확인을 해주세요.")
	private String password2;
	
	@NotBlank(message = "이메일은 필수 입력 항목입니다!")
	@Email(message = "이메일 형식에 맞게 작성해 주세요.")
	private String email;
	
	@NotNull(message = "가입 유형은 필수 입력 항목입니다.")
	private Role role;

}
