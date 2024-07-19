package com.poly.service;

import java.util.List;

import com.poly.dao.dto.VideoLikedInfo;

public interface StatsService {
	List<VideoLikedInfo> findVideoLikedInfos();
}
