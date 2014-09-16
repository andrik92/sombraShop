package com.andriyprokip.sombrashop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.andriyprokip.sombrashop.listener.Listener;

@MappedSuperclass
@EntityListeners(Listener.class)
public class AbstractEntity {
	@Column(name = "Date")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
