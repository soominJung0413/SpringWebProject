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

<script type="text/javascript">
$(function(){
	var regex = new RegExp("(.?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	$("#uploadBtn").on("click",function(e){
		var formData = new FormData();
		
		var inputFile = $("input:file[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		for(var i = 0; i<files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url : "/uploadAjaxAction",
			processData: false,
			contentType:false,
			data:formData,
			type:"post",
			dataType:"json",
			success:function(result, status, xhr){
				console.log(result);
			}
		});
	});
});
</script>

<body>
<h1>Upload With Ajax</h1>
 <div class="uploadDiv">
 	<input type="file" name="uploadFile" multiple="multiple" />
 </div>
 	
 	<button id="uploadBtn">Upload</button>
 	
 	<!-- <script type="text/javascript">
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
 	</script> -->
</body>
</html>