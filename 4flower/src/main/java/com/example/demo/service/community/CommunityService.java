package com.example.demo.service.community;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.community.Community;
import com.example.demo.mapper.community.CommunityMapper;
import com.example.demo.paging.PaginationInfo;

@Service
public class CommunityService {
	@Autowired
	private CommunityMapper communityMapper;

	public List<Community> selectAllCommunity(Community params) {
		List<Community> communityList = Collections.emptyList();

		int communityTotalCount = communityMapper.selectCommunityTotalCount();

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(communityTotalCount);

		params.setPaginationInfo(paginationInfo);

		if(communityTotalCount > 0) {
			communityList = communityMapper.selectAllCommunity(params);
		}

		return communityList;
	}

	public void insertCommunity(Community community) {
		communityMapper.insertCommunity(community);
	}

	public void updateCommunity(Community community) {
		communityMapper.updateCommunity(community);
	}

	public Community selectCommunityById(int id) {
		return communityMapper.selectCommunityById(id);
	}

}
