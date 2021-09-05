package com.example.demo.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.demo.entity.community.Community;

@Component
@Mapper
public interface CommunityMapper {

	List<Community> selectAllCommunity(Community params);

	public int selectCommunityTotalCount();

	public void insertCommunity(@Param("param") Community community);

	public void updateCommunity(@Param("param") Community community);

	public Community selectCommunityById(int id);

}
