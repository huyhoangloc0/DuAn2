package com.poly.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.poly.dao.HistoryDAO;
import com.poly.dao.impl.HistoryDAOImpl;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;

public class HistoryServiceImpl implements HistoryService {
	private HistoryDAO dao;
	private VideoService videoservice = new VideoServiceImpl();
	public HistoryServiceImpl() {
		dao = new HistoryDAOImpl();
	}
	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserIsLiked(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
	    
	    History exHistory = findByUserIdAndVideoId(user.getId(), video.getId());
	    if (exHistory == null) {
	        exHistory = new History();
	        exHistory.setUser(user);
	        exHistory.setVideo(video);
	        exHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
	        exHistory.setLike(Boolean.FALSE);
	        return dao.create(exHistory);
	    } else {
	        return exHistory;
	    }
	}


	@Override
	public Boolean updateLikeOrUnLike(User user, String videoHref) {
		Video video = videoservice.findByHref(videoHref);
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if(existHistory.isLike()==Boolean.FALSE) {
			existHistory.setLike(Boolean.TRUE);
			existHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
			
		}else {
			existHistory.setLike(Boolean.FALSE);
			existHistory.setLikeDate(null);
		}
		History updateHistory = dao.update(existHistory);
		return updateHistory != null  ? true : false;
	}

}
