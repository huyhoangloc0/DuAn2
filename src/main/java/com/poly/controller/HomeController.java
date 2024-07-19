package com.poly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.impl.HistoryServiceImpl;
import com.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/index", "/favorites", "/history" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1517115637537021552L;
	private static final int VIDEO_MAX_PAGE_SIZE = 3;
	private VideoService videoservice = new VideoServiceImpl();
	private HistoryService historyservice = new HistoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/index":
			doGetIndex(req, resp);
			break;
		case "/favorites":
			doGetFavorites(session, req, resp);
			break;
		case "/history":
			doGetHistory(session, req, resp);
			break;
		}
	}

	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoservice.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		String pageNumber = req.getParameter("page");

		List<Video> videos;

		if (pageNumber == null ||  Integer.valueOf(pageNumber) > maxPage) {
			videos = videoservice.findAll(1, VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", 1);
		} else {
			videos = videoservice.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}

		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}

	private void doGetFavorites(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyservice.findByUserIsLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		histories.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/favorites.jsp").forward(req, resp);
	}

	private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyservice.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		histories.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);
	}
}
