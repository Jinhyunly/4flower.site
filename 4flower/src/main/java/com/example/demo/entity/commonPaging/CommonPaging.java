package com.example.demo.entity.commonPaging;

import com.example.demo.paging.Criteria;
import com.example.demo.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonPaging extends Criteria{

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;
}
