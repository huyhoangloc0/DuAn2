package com.poly.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import com.poly.constant.SessionAttr;
import com.poly.entity.Video;
import com.poly.entity.User;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;
import com.poly.entity.History;
import com.poly.service.HistoryService;
import com.poly.service.impl.HistoryServiceImpl;

import javassist.SerialVersionUID;
@WebServlet(urlPatterns =  "/video")
public class VideoController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5122669106734571805L;
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		
		
		switch(actionParam) {
			case "watch":
				doGetWatch(session,href,req,resp);
				break;
			case "like":
				doGetLike(session,href,req,resp);
				break;
		}
	}
		private void doGetWatch(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Video video = videoService.findByHref(href);
			req.setAttribute("video", video);
			
			User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			if(currentUser != null) {
				History history = historyService.create(currentUser, video);
				req.setAttribute("flagLikeBtn", history.isLike());
			}else {
				req.setAttribute("flagLikeBtn", false);
			}
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/video-detail.jsp");
			requestDispatcher.forward(req, resp);
		}
		
		private void doGetLike(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("application/json");
			User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
			boolean result = historyService.updateLikeOrUnLike(currentUser, href);
			if(result == true) {
				resp.setStatus(204);
			}else {
				resp.setStatus(400);
			}
		}
		
}

