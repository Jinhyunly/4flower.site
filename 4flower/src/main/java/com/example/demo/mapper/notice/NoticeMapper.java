package com.example.demo.mapper.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.demo.entity.notice.Notice;

@Component
@Mapper
public interface NoticeMapper {

	List<Notice> selectAllNotice(Notice params);

	public int selectNoticeTotalCount();

	public void insertNotice(@Param("param") Notice notice);

	public void updateNotice(@Param("param") Notice notice);

	public Notice selectNoticeById(int id);

}
