<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Pass</title>
    <%@include file="/common/head.jsp" %>
</head>
<body>
<%@include file="/common/nav.jsp" %>
<div class="container mt-4">
    <%@include file="/common/draw.jsp" %>
    <div class="container mt-5" style="margin-bottom: 300px">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">Forgot Pass</div>
                    <div class="card-body">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <button type="submit" id="sendBtn" class="btn btn-primary">Send</button>
                            <h5 style="color: red" id="messageReturn"></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="/common/footer.jsp" %> 
</body>
<script >
	$('#sendBtn').click(function() {
		$('#messageReturn').text('');
		var email = $('#email').val();
		var formData = {'email' : email};
		$.ajax({
			url: 'forgotPass',
			type: 'POST',
			data: formData
		}).then(function (data) {
			$('#messageReturn').text('Password has been reset, please check your email');
			setTimeout(function () {
				window.location.href = 'http://localhost:8080/asm-java4/index';
			}, 5*1000);
		}).fail(function (error) {
			$('#messageReturn').text('Your information is not correct, try again');
		});
	});
</script>
</html>

