package com.example.demo.entity.community;

import com.example.demo.entity.commonPaging.CommonPaging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Community extends CommonPaging{

	int id;
	String title;
	String content;
	int user_id;
	String add_date;
	String add_torcd;
	String update_date;
	String update_torcd;
	int update_count;
	String ent_kbn;


}
