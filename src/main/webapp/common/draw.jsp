<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="drawer" class="drawer d-lg-block">
        <div class="drawer-content">
            <ul>
            	<c:choose>
            	<c:when test="${not empty sessionScope.currentUser}">
            		<li><a href="index"><i class="fas fa-home"></i> Trang chủ</a></li>
            		<li><a href="#"><i class="fas fa-user-plus"></i> Kênh đăng ký</a></li>
            		<hr>
            		<li><a href="#"><i class="fas fa-user"></i> Bạn</a></li>
            		<li><a href="#"><i class="fas fa-folder"></i> Kênh của bạn</a></li>
            		<li><a href="favorites"><i class="fas fa-thumbs-up"></i> Video đã like</a></li>
	                <li><a href="history"><i class="fas fa-thumbs-up"></i> Lịch sử xem</a></li>
	                <li><a href="#"><i class="fas fa-video"></i> Video đã đăng</a></li>
            	</c:when>
            	<c:otherwise>
            		<li><a href="index"><i class="fas fa-home"></i> Trang chủ</a></li>

            	</c:otherwise>
            </c:choose>
            </ul>
            
        </div>
      </div>