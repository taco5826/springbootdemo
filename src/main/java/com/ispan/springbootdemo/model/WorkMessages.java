package com.ispan.springbootdemo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="work_messages")
public class WorkMessages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	//https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
	// 搭配 controller @Valid, BindingResult
	@Size(min=2, max=199, message="請輸入2到199個字串")
//	@Email(message="請輸入 Email")
	@Column(name="text",columnDefinition = "nvarchar(200)")
	private String text;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="added",columnDefinition = "datetime")
	private Date added;
	
	public WorkMessages() {
	}

	@PrePersist  // 在轉換到 Persist 狀態以前去做以下方法
	public void onCreate() {  //系統產生時間
		if(added==null) {
		added = new Date();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	@Override
	public String toString() {
		return "WorkMessages [id=" + id + ", text=" + text + ", added=" + added + "]";
	}
	
	
}
