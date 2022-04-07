package com.ispan.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ispan.springbootdemo.model.WorkMessages;
import com.ispan.springbootdemo.model.WorkMessagesRepository;

@Service
public class testService {

	@Autowired
	private WorkMessagesRepository workMessagesDao;

	public void insert(WorkMessages messages) {
		workMessagesDao.save(messages);
	}

	public WorkMessages findById(Integer id) {

		Optional<WorkMessages> optional = workMessagesDao.findById(id);

		if (optional.isPresent()) {
			optional.get();
		}
		return null;
	}

	public void deleteById(Integer id) {
		workMessagesDao.deleteById(id);
	}

	public List<WorkMessages> findAllMessages() {
		return workMessagesDao.findAll();
	}

	public Page<WorkMessages> findByPage(Integer pageNunber) {
		Pageable pgb = PageRequest.of(pageNunber - 1, 3, Sort.Direction.DESC, "added");
		Page<WorkMessages> page = workMessagesDao.findAll(pgb);
		return page;
	}
	
	public WorkMessages getLastest() {
		return workMessagesDao.findFirstByOrderByAddedDesc();
	}
	
}
