package com.andriyprokip.sombrashop.util;

import org.springframework.data.domain.Page;

public class PagingUtil<E> {
	private Page<E> page;
	private int current;
	private int begin;
	private int end;

	public int getTotalPages() {
		return page.getTotalPages();
	}

	public int getNumber() {
		return page.getNumber();
	}

	public Page<E> getPage() {
		return page;
	}

	public int getCurrent() {
		return current;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public PagingUtil(Page<E> inputPage) {
		page = inputPage;
		current = inputPage.getNumber() + 1;
		begin = Math.max(1, current - 5);
		end = Math.min(begin + 10, inputPage.getTotalPages());
	}
}
