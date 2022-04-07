package com.ispan.springbootdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ispan.springbootdemo.dto.MessageDto;
import com.ispan.springbootdemo.model.WorkMessages;
import com.ispan.springbootdemo.service.WorkMessagesService;

@Controller
public class MessageController {

	@Autowired
	private WorkMessagesService messagesService;

	@PostMapping("/message/add")
	public ModelAndView addMessage(ModelAndView mav, @Valid @ModelAttribute(name = "workMessages") WorkMessages msg,
			BindingResult br) {

		if (!br.hasErrors()) {

			messagesService.insert(msg);
			WorkMessages newMsg = new WorkMessages();
			mav.getModel().put("workMessages", newMsg);

		}

		WorkMessages latestMsg = messagesService.getLastest();
		mav.getModel().put("lastMessage", latestMsg);
		mav.setViewName("addMessages");

		return mav;
	}

	@GetMapping("/message/editMessage")
	public String editMessage(Model model, @RequestParam(name = "id") Integer id) {

		WorkMessages msg = messagesService.findById(id);
		model.addAttribute("workMessages", msg);

		return "editMessage";
	}

	@PostMapping("message/editMessage")
	public ModelAndView editMessage(ModelAndView mav, @Valid @ModelAttribute(name = "workMessages") WorkMessages msg,
			BindingResult br) {

		mav.setViewName("editMessage");

		if (!br.hasErrors()) {
			https: // docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence.saving-entites
			messagesService.insert(msg);
			mav.setViewName("redirect:/message/viewMessages");
		}
		return mav;
	}

	@GetMapping("message/deleteMessage")
	public ModelAndView deleteMessage(ModelAndView mav, @RequestParam(name = "id") Integer id) {
		messagesService.deleteById(id);
		mav.setViewName("redirect:/message/viewMessages");
		return mav;
	}

	@PostMapping("/api/postMessage")
	@ResponseBody
	public List<WorkMessages> postMessageApi(@RequestBody MessageDto dto) {
		String text = dto.getMsg();

		WorkMessages workMsg = new WorkMessages();
		workMsg.setText(text);
		messagesService.insert(workMsg);

		Page<WorkMessages> page = messagesService.findByPage(1);

		List<WorkMessages> list = page.getContent();

		return list;
	}
}
