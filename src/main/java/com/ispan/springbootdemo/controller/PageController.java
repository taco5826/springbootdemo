package com.ispan.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ispan.springbootdemo.model.WorkMessages;
import com.ispan.springbootdemo.service.WorkMessagesService;

@Controller
public class PageController {

	@Autowired
	private WorkMessagesService messagesService;

	@GetMapping("/")
	public String welcomeIndex() {
		return "index";
	}

//		@GetMapping("/message/add")
//		public String addMessagePage() {
//			return "addMessages";
//		}

	@GetMapping("/message/add")
	public ModelAndView addMessagePage(ModelAndView mav) {

		WorkMessages message = new WorkMessages();
		mav.getModel().put("workMessages", message);

		WorkMessages lastMsg = messagesService.getLastest();
		mav.getModel().put("lastMessage", lastMsg);

		mav.setViewName("addMessages");
		return mav;
	}

	@GetMapping("/message/viewMessages")
	public ModelAndView viewMessages(ModelAndView mav,
			@RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
		Page<WorkMessages> page = messagesService.findByPage(pageNumber);

		mav.getModel().put("page", page);
		mav.setViewName("viewMessages");

		return mav;
	}
	
	@GetMapping("/message/ajax")
	public String ajaxPage() {
		return"ajax-messages";
	}
}
