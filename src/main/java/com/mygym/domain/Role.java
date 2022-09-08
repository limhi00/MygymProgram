package com.mygym.domain;

import lombok.Getter;

@Getter
public enum Role {
	
	ROLE_MEMBER("회원"), ROLE_TRAINER("트레이너"), ROLE_ADMIN("관리자");
	
    private String value;

    Role(String value) {
        this.value = value;
    }
}