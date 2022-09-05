package com.mygym.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
	
	@Size(min = 3, max = 25)
	@NotBlank(message = "ID는 필수항목이며, 공백이 들어갈 수 없습니다.")
	private String username;
	
	@Size(min = 3, max = 10, message = "최소 2글자 이상 입력해 주세요.")
	@NotBlank(message = "이름은 필수항목이며, 공백이 들어갈 수 없습니다.")
	private String name;
	
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phone;
	
	@Size(min = 3, max = 10)
	@NotBlank(message = "비밀번호는 필수항목입니다.")
	private String password1;
	
	@Size(min = 3, max = 10)
	@NotBlank(message = "비밀번호 확인을 해주세요.")
	private String password2;
	
	@NotBlank(message = "이메일은 필수항목입니다! 형식에 맞게 작성해주세요.")
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	@CreationTimestamp
//	private LocalDateTime createDate; 
	
}
