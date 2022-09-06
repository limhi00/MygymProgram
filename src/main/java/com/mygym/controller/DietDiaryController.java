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

import com.mygym.domain.DietDiary;
import com.mygym.domain.FullCalendarDto;
import com.mygym.service.DietDiaryService;

@Controller
public class DietDiaryController {

	@Autowired
	private DietDiaryService ddiaryService;
	
	@GetMapping("/dietCalendarView")
	public String dietCalendarView() {
		
		return "diet/dietCalendar";
	}
	
	@GetMapping("/dietCalendarList")
	@ResponseBody
	public Map<String, FullCalendarDto> dietCalendarList(Principal principal) {
		
		Map<String, FullCalendarDto> eventMap = new HashMap<>();
		List<DietDiary> ddiaryList = ddiaryService.getDiaryList(principal.getName());
		
		int count = 0;
		for (DietDiary dd : ddiaryList) {
//			System.out.println("ddiaryList" + dd);
			FullCalendarDto vo = new FullCalendarDto();
			vo.setTitle("식단 확인");
			vo.setStart(dd.getD_indate());
			vo.setUrl("/getDiary?dseq="+dd.getDseq());
			vo.setAllDay(true);
			vo.setColor("#2C3E50");
			vo.setTextColor("#fff");
			
			eventMap.put("event"+count, vo);
			++count;
		}
		
		return eventMap;
	}
	
	@GetMapping("/getDiary")
	public String getDdiary(@RequestParam("dseq") Long dseq, Model model, DietDiary ddiary) {
		
//		System.out.println("dseq=" + dseq);
		
		ddiary = ddiaryService.getDiary(dseq);
//		System.out.println("ddiary = " + ddiary);
		model.addAttribute("ddiary", ddiary);
		
		return "diet/getDiary";
	}

	@GetMapping("/insertDiaryView")
	public String insertDdiaryForm(Model model, @RequestParam String d_indate) {
		
		model.addAttribute("d_indate", d_indate);
		
		return "diet/insertDiary";
	}
	
	@PostMapping("/insertDiary")
	public String insertDdiary(RedirectAttributes rattr, DietDiary ddiary) {
		
		Long dseq = ddiaryService.insertDiary(ddiary);
		rattr.addAttribute("dseq", dseq);
		
		return "redirect:/getDiary";
	}
	
	@GetMapping("/updateDiary")
	public String updateDdiaryView(Model model, DietDiary ddiary) {
		
		ddiary = ddiaryService.getDiary(ddiary.getDseq());
		model.addAttribute("ddiary", ddiary);
		
		return "diet/updateDiary";
	}
	
	@PostMapping("/updateDiary")
	public String updateDdiary(RedirectAttributes rattr, DietDiary ddiary) {
		
		ddiaryService.updateDiary(ddiary);
		rattr.addAttribute("dseq", ddiary.getDseq());
		
		return "redirect:/getDiary";
	}
	
	
//	@GetMapping("/getDiary")
//	public String getDiary(DietDiary ddiary, Model model){
//		model.addAttribute("ddiary", ddiaryservice.getDietDiary(ddiary));
//		
//		return "diet/getDiary";
//	}
//	
//	@PostMapping("/insertGetDiary")
//	public String insertGetDiary(DietDiary ddiary,RedirectAttributes redirectAttributes) {
//		
//		System.out.println("Diary =" + ddiary);
//		long dseq = ddiaryservice.insertDietDiary(ddiary);
//		System.out.println("insertGetDiary() : dseq="+dseq);
//		redirectAttributes.addAttribute("dseq", dseq);
//		
//		return "redirect:/getDiary";
//	}
	
}
