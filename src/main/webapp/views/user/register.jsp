<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <%@ include file="/common/head.jsp" %>
</head>
<body>
    <%@ include file="/common/nav.jsp" %>
    <div class="container mt-4">
        <%@ include file="/common/draw.jsp" %>
        <div class="container mt-5" style="margin-bottom: 300px">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">Đăng ký</div>
                        <div class="card-body">
                            <form action="register" method="post">
                                <div class="form-group">
                                    <label for="username">Tên đăng nhập:</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu:</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <div class="form-group">
                                    <label for="confirm_password">Nhập lại mật khẩu:</label>
                                    <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Đăng ký</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <%@ include file="/common/footer.jsp" %> 
    </body>
</html>
