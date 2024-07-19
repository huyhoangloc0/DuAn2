package com.poly.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.constant.SessionAttr;
import com.poly.dao.dto.UserDto;
import com.poly.dao.dto.VideoLikedInfo;
import com.poly.entity.User;
import com.poly.service.StatsService;
import com.poly.service.UserService;
import com.poly.service.impl.StatsServiceImpl;
import com.poly.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin","/admin/favorites"}, name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = -2192301184202943572L;
	private UserService userService = new UserServiceImpl();
	private StatsService statsService = new StatsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			String path = req.getServletPath();
			switch (path) {
			case "/admin":
				doGetHome(req, resp);
				break;
			case "/admin/favorites":
				doGetFavorites(req, resp);
				break;
			
			}
		}else {
			resp.sendRedirect("index");
		}
	}

	private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<VideoLikedInfo> videos = statsService.findVideoLikedInfos();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
		
	}
	
	private void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<UserDto> users = userService.findUsersLikedVideoByVideoHref(videoHref);
		if(users.isEmpty()) {
			resp.setStatus(400);
		}else {
			ObjectMapper mapper = new ObjectMapper();
			String dataResponse = mapper.writeValueAsString(users);
			resp.setStatus(200);
			out.print(dataResponse);
			out.flush();
		}
	}
}
