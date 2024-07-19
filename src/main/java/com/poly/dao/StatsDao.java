package com.poly.dao;

import java.util.List;

import com.poly.dao.dto.VideoLikedInfo;
import com.poly.entity.User;

public interface StatsDao {
	List<VideoLikedInfo> findVideoLikedInfos();
	
}
