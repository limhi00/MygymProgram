package com.mygym.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mygym.domain.Reservation;
import com.mygym.domain.ReservationListDto;
import com.mygym.service.ReservationService;

@Controller
public class TrainerController {

	@Autowired
	private ReservationService resService;
	
	@GetMapping("/classScheduleView")
	public String classScheduleView() {
		
		return "trainer/classSchedule";
	}
	
	@GetMapping("/classScheduleList")
	@ResponseBody
	public Map<String, ReservationListDto> classScheduleList(Principal principal) {

		Map<String, ReservationListDto> eventMap = new HashMap<>();
		List<Reservation> resList = resService.getReservationList(principal.getName());
		
		int count = 0;
		for (Reservation re : resList) {
			ReservationListDto vo = new ReservationListDto();
			vo.setTitle("수업 확인");
			vo.setStart(re.getClassDate()+"T"+re.getClassTime()+":00:00");
			vo.setEnd(re.getClassDate()+"T"+re.getClassTime()+":50:00");
			vo.setUrl("/classChecking?rseq="+re.getRseq());
			vo.setColor("#ff4800");
			vo.setTextColor("#fff");
			
			eventMap.put("event"+count, vo);
			++count;
		}
		
		return eventMap;
	}
}
