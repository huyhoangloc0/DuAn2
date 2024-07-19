<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-body">
    <button class="btn btn-light mr-3" onclick="document.getElementById('drawer').classList.toggle('open')">
        <i class="fas fa-bars"></i>
    </button>
    <a class="navbar-brand" href="<c:url value='/index'/>">
        <img src="<c:url value='/templates/user/svg/logo.svg' />" >
    </a>
    <form class="form-inline my-2 my-lg-0 search-form mx-auto">
        <input class="form-control mr-sm-2 search-input" type="search" placeholder="Tìm kiếm" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0 search-button" type="submit">
            <i class="fas fa-search"></i>
        </button>
    </form>
    <ul class="navbar-nav ms-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img src="https://via.placeholder.com/50" class=" rounded-circle" alt="User Avatar">
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <c:choose>
            	<c:when test="${not empty sessionScope.currentUser}">
            		<a class="dropdown-item" href="index">Xin chào, ${sessionScope.currentUser.username}</a>
            		<a class="dropdown-item" href="logout">Đăng xuất</a>
                	<a class="dropdown-item" href="#" data-toggle="modal" data-target="#chanepassmodel">Đổi mật khẩu</a>
                	<a class="dropdown-item" href="#">Cập nhật tài khoản</a>
            	</c:when>
            	<c:otherwise>
            		<a class="dropdown-item" href="register">Đăng ký</a>
                	<a class="dropdown-item" href="login">Đăng nhập</a>
                	<a class="dropdown-item" href="forgotPass">Quên mật khẩu</a>
            	</c:otherwise>
            </c:choose>
            </div>
        </li>
    </ul>
</nav>
<div class="modal fade" id="chanepassmodel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Change password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<div class="form-group">
	            <label for="password">Mật khẩu cũ:</label>
	            <input type="password" class="form-control" id="currentPass" name="oldpass" required>
            </div>
            <div class="form-group">
	            <label for="password">Mật khẩu mới:</label>
	            <input type="password" class="form-control" id="newPass" name="newpass" required>
            </div>
            <h5 style="color: red" id="messageChanePass"></h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-warning" id="changePassBtn">Save changes</button>
      </div>
    </div>
  </div>
</div>
