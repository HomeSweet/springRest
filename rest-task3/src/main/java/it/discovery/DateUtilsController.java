package it.discovery;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/utils")
@Controller
public class DateUtilsController {
	
	@ResponseBody
	@RequestMapping("/time")
	public String getCurrentTime() {
		return LocalTime.now().toString();
	}

	@ResponseBody
	@RequestMapping("/date")
	public String getCurrentDate() {
		return LocalDate.now().toString();
	}
}
