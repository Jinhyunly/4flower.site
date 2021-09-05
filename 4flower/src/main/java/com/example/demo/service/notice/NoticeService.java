package com.example.demo.service.notice;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.notice.Notice;
import com.example.demo.mapper.notice.NoticeMapper;
import com.example.demo.paging.PaginationInfo;

@Service
public class NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	public List<Notice> selectAllNotice(Notice params) {
		List<Notice> noticeList = Collections.emptyList();

		int noticeTotalCount = noticeMapper.selectNoticeTotalCount();

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(noticeTotalCount);

		params.setPaginationInfo(paginationInfo);

		if(noticeTotalCount > 0) {
			noticeList = noticeMapper.selectAllNotice(params);
		}

		return noticeList;
	}

	public void insertNotice(Notice notice) {
		noticeMapper.insertNotice(notice);
	}

	public void updateNotice(Notice notice) {
		noticeMapper.updateNotice(notice);
	}

	public Notice selectNoticeById(int id) {
		return noticeMapper.selectNoticeById(id);
	}
}
