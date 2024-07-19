package com.poly.service.impl;

import java.util.List;

import com.poly.dao.VideoDAO;
import com.poly.dao.impl.VideoDAOImpl;
import com.poly.entity.Video;
import com.poly.service.VideoService;

public class VideoServiceImpl implements VideoService {
	public VideoDAO dao;
	public VideoServiceImpl() {
		dao = new VideoDAOImpl();
	}
	@Override
	public Video findByID(Integer ID) {
		return dao.findByID(ID);
	}

	@Override
	public Video findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		entity.setActive(Boolean.TRUE);
		entity.setViews(0);
		entity.setShares(0);
		return dao.create(entity);
		
	}

	@Override
	public Video update(Video entity) {
		return dao.update(entity);
	}

	@Override
	public Video delete(String href) {
		Video entity = findByHref(href);
		entity.setActive(Boolean.FALSE);
		return dao.update(entity);
	}

}
