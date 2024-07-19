package com.poly.dao.impl;

import java.util.List;

import com.poly.dao.AbstractDAO;
import com.poly.dao.HistoryDAO;
import com.poly.entity.History;

public class HistoryDAOImpl extends AbstractDAO<History> implements HistoryDAO  {

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o Where o.user.username=?0 AND o.video.isActive =1"
				+ " order by o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserIsLiked(String username) {
		String sql = "SELECT o FROM History o WHere o.user.username=?0 AND o.isLiked=1 "
				+ "AND o.video.isActive =1"
				+ " order by o.viewedDate DESC";
		return super.findMany(History.class, sql, username);	
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "Select o FROM History o WHERE o.user.id =?0 AND o.video.id = ?1"
				+ " AND o.video.isActive=1";
		return super.findOne(History.class, sql, userId,videoId);
	}


}
