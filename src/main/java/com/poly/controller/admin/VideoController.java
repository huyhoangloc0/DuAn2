package com.poly.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/admin/video"}, name = "VideoControllerOfAdmin")
public class VideoController extends HttpServlet{
	private static final long seriaVersionUID = 3968414433292121855L;
	private VideoService videoService = new VideoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			String action = req.getParameter("action");
			switch(action) {
				case "view":
					dogetOverView(req, resp);
					break;
				case "delete":
					dogetDelete(req,resp);
					break;
				case "add":
					req.setAttribute("isEdit", false);
					dogetAdd(req,resp);
					break;
				case "edit":
					req.setAttribute("isEdit", true);
					dogetEdit(req,resp);
					break;
			}
//		}else {
//			resp.sendRedirect("index");
//		}
	}
	private void dogetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href = req.getParameter("href");
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			String action = req.getParameter("action");
			switch(action) {
				case "add":
					doPostAdd(req, resp);
					break;
				case "edit":
					doPostEdit(req,resp);
					break;
				
			}
		}else {
			resp.sendRedirect("index");
		}
	}
	
	private void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("newHref");
		String poster = req.getParameter("poster");
		String hrefOrigin = req.getParameter("hrefOrigin");
		
		Video video = videoService.findByHref(hrefOrigin);
		video.setTitle(title);
		video.setDescription(description);
		video.setHref(href);
		video.setPoster(poster);
		
		
		Video videoReturn = videoService.update(video);
		if(videoReturn != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
		
	}
	private void dogetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
	}

	private void dogetOverView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/video-overview.jsp").forward(req, resp);
	}
	
	private void dogetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		Video videoDeleted = videoService.delete(href);
		if(videoDeleted != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	
	private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		
		Video video = new Video();
		video.setTitle(title);
		video.setDescription(description);
		video.setHref(href);
		video.setPoster(poster);
		
		
		Video videoReturn = videoService.create(video);
		if(videoReturn != null) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
}
