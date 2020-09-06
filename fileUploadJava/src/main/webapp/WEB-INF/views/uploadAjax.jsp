<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
 <div class="uploadDiv">
 	<input type="file" name="uploadFile" multiple="multiple" />
 </div>
 	
 	<button id="uploadBtn">Upload</button>
 	
 	<script type="text/javascript">
 		$(document).ready(function(){
 			$("#uploadBtn").on("click",function(e){
 				
 				var formData = new FormData();
 	 			
 	 			var inputFile = $("input[name='uploadFile']");
 	 			
 	 			var files = inputFile[0].files;
 	 			
 	 			console.log(files);
 	 			
 	 			//add filedate to formData
 	 			for(var i =0; i<files.length; i ++){
 	 				formData.append("uploadFile", files[i]);
 	 			}
 	 			console.log(formData);
 	 			$.ajax({
 	 				url:'/uploadAjaxAction',
 	 				processData:false,
 	 				contentType:false,
 	 				data:formData,
 	 				type:'post',
 	 				success:function(result){
 	 					alert('Uploaded');
 	 				}
 	 			});
 				
 			});
 			
 		});
 	</script>
</body>
</html>