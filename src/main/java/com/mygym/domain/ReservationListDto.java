package com.mygym.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationListDto {
	private String title;
	private String start;
	private String end;
	private String url;
	private String color;
	private String textColor;
}
