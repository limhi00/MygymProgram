package com.mygym.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mygym.domain.Reservation;
import com.mygym.domain.ReservationListDto;
import com.mygym.service.ReservationService;

@Controller
public class ClassController {
	
	@Autowired
	private ReservationService resService;
	
	@GetMapping("/classCalendarView")
	public String classCalendarView() {
		
		return "class/classCalendar";
	}
	
	@GetMapping("/classCalendarList")
	@ResponseBody
	public Map<String, ReservationListDto> classCalendarList(Principal principal) {
		
		Map<String, ReservationListDto> eventMap = new HashMap<>();
		List<Reservation> resList = resService.getReservationList(principal.getName());
		
		int count = 0;
		for (Reservation re : resList) {
			System.out.println("resList" + re);
			ReservationListDto vo = new ReservationListDto();
			vo.setTitle("수업 확인");
			vo.setStart(re.getClassDate());
			vo.setUrl("/classChecking?rseq="+re.getRseq());
			vo.setAllDay(true);
			vo.setColor("#eee");
			vo.setTextColor("#000");
			
			eventMap.put("event"+count, vo);
			++count;
		}
		
		return eventMap;
	}
	
	@GetMapping("/classChecking")
	public String classChecking(@RequestParam("rseq") Long rseq, Model model, Reservation res) {
		
		System.out.println("rseq="+rseq);
		res = resService.getReservation(rseq);
		System.out.println("reservation="+res);
		model.addAttribute("reservation", res);
		
		return "class/classChecking";
	}
	
	@GetMapping("/classReservation")
	public String classReservationView(Model model, @RequestParam String classDate) {
		
		model.addAttribute("classDate", classDate);
		
		return "class/classReservation";
	}
	
	@PostMapping("/classReservation")
	public String classReservation(RedirectAttributes rattr, Reservation res) {
		
		Long rseq = resService.insertReservation(res);
		rattr.addAttribute("rseq", rseq);
		
		return "redirect:/classChecking";
	}
	
	@GetMapping("/classCancle")
	public String classCancleView(Model model, Reservation res) {
		
		res = resService.getReservation(res.getRseq());
		model.addAttribute("reservation", res);
		
		return "class/classCancle";
	}
	
	@PostMapping("/classCancle")
	public String classCancle(Reservation res) {
		
		resService.deleteReservation(res);
		
		return "redirect:/classCalendarView";
	}
}
