<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${video.title}</title>
    <%@include file="/common/head.jsp" %>
</head>
<body><%@include file="/common/nav.jsp" %>
	<%@include file="/common/draw.jsp" %>
<div class="container mt-4">
	<main class="m-auto p-4 d-flex">
        <article class="col-md-8 p-0 mr-5">
            <div style="width: 100%; height: 500px;">
			    <iframe id="tm-video" src="http://www.youtube.com/embed/${video.href}" style="width: 100%; height: 100%;"></iframe>
			</div>
              <h2 class="mt-4">${video.title}</h2>
              <div class="media row  mt-3 justify-content-end align-items-center">
                <div class="col-6 d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" class="mr-3 rounded-circle" alt="User Avatar">
                    <div class="media-body">
                        <h5 class="mt-0">${video.id}</h5>
                    </div>
                    <button type="button" class="btn btn-dark rounded-pill mr-2">Đăng ký</button>
                </div>
                <div class="col-6 d-flex justify-content-end align-items-center">
                <c:if test="${not empty sessionScope.currentUser}">
                	<button id="likeOrUnlikeBtn" class="btn btn-primary mr-2 p-2 rounded-pill">
					    <c:choose>
					    	<c:when test="${flagLikeBtn==true}">
					    		UnLike
					    	</c:when>
					    	<c:otherwise>Like</c:otherwise>
					    </c:choose>
					</button>
					
					<a href="#" class="btn btn-secondary mr-1 ps-2 rounded-pill">
					    <i class="fas fa-share"></i> Share
					</a>
                </c:if>
                <input id="videoIdHdn" type="hidden" value="${video.href}">
                </div>
            </div>
            
            <p class="mt-3 p-2 rounded bg-light" style="min-height: 300px; border: 1px solid #ccc;">${video.description}</p>
              
              <div class="mt-4">
                <h3>Comments</h3>
                <div class="media mt-3">
                    <img src="https://via.placeholder.com/50" class="mr-3 rounded-circle" alt="User Avatar">
                    <div class="media-body">
                        <h5 class="mt-0">User Name</h5>
                        Comment content goes here.
                    </div>
                </div>
            </div>
        </article>
        <%@include file="/common/aside.jsp" %>
    </main>
</div>
	<%@include file="/common/footer.jsp" %>
	<script >
		$('#likeOrUnlikeBtn').click(function(){
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url: 'video?action=like&id='+videoId
			}).then(function(data) {
				var text = $('#likeOrUnlikeBtn').text();
				if(text.indexOf('Like') != -1){
					$('#likeOrUnlikeBtn').text('Unlike');
				}else{
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function(error){
				alert('Oops !!! Please try again');
			});
		});
	</script>
</body>
</html>
    