package com.andriyprokip.sombrashop.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.andriyprokip.sombrashop.entity.AbstractEntity;

public class Listener {
	@PrePersist
	public void setFirstModifiedDate(AbstractEntity entity) {
		entity.setDate(new Date());
	}

	@PreUpdate
	public void setLastModifiedDate(AbstractEntity entity) {
		entity.setDate(new Date());
	}
}
