<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.servletContext.contextPath}/sample/exUploadPost" method="post" enctype="multipart/form-data">
		<div>
			<input type="file" name="files" id="" />
		</div>
		<div>
			<input type="file" name="files" id="" />
		</div>
		<div>
			<input type="file" name="files" id="" />
		</div>
		<div>
			<input type="file" name="files" id="" />
		</div>
		<div>
			<input type="file" name="files" id="" />
		</div>
		<input type="submit" value="제출" />
	</form>
</body>
</html>