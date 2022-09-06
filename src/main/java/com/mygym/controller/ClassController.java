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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mygym.domain.ClassDiary;
import com.mygym.domain.Member;
import com.mygym.domain.Reservation;
import com.mygym.domain.FullCalendarDto;
import com.mygym.service.ReservationService;

@Controller
public class ClassController {
	
	@Autowired
	private ReservationService resService;
	
	@GetMapping("/classCalendar")
	public String classCalendar() {
		
		return "class/classCalendar";
	}
	@GetMapping("/classCalendarView")
	public String classCalendarView() {
		
		return "redirect:/classCalendar";
	}
	
	@GetMapping("/classCalendarList")
	@ResponseBody
	public Map<String, FullCalendarDto> classCalendarList(Principal principal) {
		
		Map<String, FullCalendarDto> eventMap = new HashMap<>();
		List<Reservation> resList = resService.getReservationList(principal.getName());
		
		int count = 0;
		for (Reservation re : resList) {
			FullCalendarDto vo = new FullCalendarDto();
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
	
	@GetMapping("/classChecking")
	public String classChecking(@RequestParam("rseq") Long rseq, Model model, Reservation res) {
		
//		System.out.println("rseq="+rseq);
//		System.out.println("reservation="+res);
		res = resService.getReservation(rseq);
		String cTrainerName = resService.getCTrainerName(res.getCTrainer());
		String username = resService.getCTrainerName(res.getMember().getUsername());
		model.addAttribute("reservation", res);
		model.addAttribute("cTrainer", cTrainerName);
		model.addAttribute("username", username);
		
		return "class/classChecking";
	}
	
	@GetMapping("/classReservation")
	public String classReservationView(Principal principal, Model model, @RequestParam String classDate, String role) {
		
		model.addAttribute("cTrainerList", resService.getCTrainerList(role));
		model.addAttribute("name", principal.getName());
		model.addAttribute("classDate", classDate);
		
		return "class/classReservation";
	}
	
	@PostMapping("/classReservation")
	public String classReservation(RedirectAttributes rattr, Reservation res) {
		
		Long rseq = resService.insertReservation(res);
		
		rattr.addAttribute("rseq", rseq);
		
		return "redirect:/classChecking";
	}
	
	@GetMapping("/classCancelView")
	public String classCancleView(@RequestParam("rseq") Long rseq, Model model, Reservation res) {
		
		res = resService.getReservation(rseq);
		model.addAttribute("reservation", res);
		
		return "class/classCancel";
	}
	
	@RequestMapping("/classCancel")
	public void classCancle(Reservation res) {
		
		resService.deleteReservation(res);
	}
	
	@GetMapping("/getClassDiary")
	public String getClassDiary(Long rseq, ClassDiary cDiary, Model model) {
		
		cDiary = resService.getClassDiary(rseq);
		System.out.println("get cDiary="+cDiary);
		model.addAttribute("cDiary", cDiary);
		
		return "class/getClassDiary";
	}
	
	@RequestMapping("/insertClassDiaryView")
	public String insertClassDiaryView(Long rseq, ClassDiary cDiary, Model model) {
		
		cDiary = resService.getClassDiary(rseq);
		System.out.println("rseq="+rseq);
		System.out.println("insertView cDiary="+cDiary);
		model.addAttribute("cDiary", cDiary);
		
		return "class/insertClassDiary";
	}
	
	@RequestMapping("/insertClassDiary")
	public String insertClassDiary(Long rseq, ClassDiary cDiary) {
		
		Reservation reservation = new Reservation();
		reservation.setRseq(rseq);
		cDiary = resService.findClassDiary(reservation);
		System.out.println("insert cDiary="+cDiary);
		resService.insertClassDiary(cDiary);
		
		return "redirect:/getClassDiary";
	}
	
	@GetMapping("/classScheduleView")
	public String classScheduleView() {
		
		return "trainer/classSchedule";
	}
	
	@GetMapping("/classScheduleList")
	@ResponseBody
	public Map<String, FullCalendarDto> classScheduleList(Principal principal) {
		
		Map<String, FullCalendarDto> eventMap = new HashMap<>();
		List<Reservation> resList = resService.getTrainerReservationList(principal.getName());
		
		int count = 0;
		for (Reservation re : resList) {
			FullCalendarDto vo = new FullCalendarDto();
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
