package com.mygym.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DietDiaryListDto {
	private String title;
	private String start;
	private String url;
	private String color;
	private String textColor;
	private String rendering;
	private Boolean allDay;
}
