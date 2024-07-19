package com.poly.service.impl;

import java.util.List;

import com.poly.dao.StatsDao;
import com.poly.dao.dto.VideoLikedInfo;
import com.poly.dao.impl.StatsDaoImpl;
import com.poly.service.StatsService;

public class StatsServiceImpl implements StatsService{

	private StatsDao statsDao;
	
	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
	}
	
	@Override
	public List<VideoLikedInfo> findVideoLikedInfos() {
		return statsDao.findVideoLikedInfos();
	}

}
