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

<style type="text/css">
.uploadResult{
	width: 100%;
	background-color: gray;
}
.uploadResult ul{
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}
.uploadResult ul li{
	list-style: none;
	padding: 10px;
}
.uploadResult ul li img{
	width: 100px;
}
.uploadResult ul li span{
	color: white;
}
.bigPictureWrapper{
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background:rgba(255,255,255,0.5);
}
.bigPicture img{
	width: 600px;
}
</style>
</head>

<script type="text/javascript">
function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture")
	.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>")
	.animate({width:'100%', height:'100%'},1000);
}

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
	
	var uploadDiv = $(".uploadDiv").clone();//파일업로드 후 초기화 하기 위함
	
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
				
				$(".uploadDiv").html(uploadDiv.html());
				showUploadedFile(result);
				
			}
		});
	});
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadedResultArr){
		var str = "";
		
		$(uploadedResultArr).each(function(i, obj){
			if(!obj.image){
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				
				var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				
				
				str += "<li><div><a href='/download?fileName="+fileCallPath+"' >"+"<img src='/resources/img/attach.jpg' />"+obj.fileName+"</a>"+
						"<span data-file=\'"+fileLink+"\' data-type=\'file\'> x </span></div></li>"; 
			}else{
				var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
				
				var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				
				originPath = originPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\" ><img src='/display?fileName="+fileCallPath+"' /></a>"+
						"<span data-file=\'"+fileCallPath+"\' data-type=\'image\'> x </span></li>";
			}
		});
		uploadResult.append(str);
};

$(".bigPictureWrapper").on("click",function(e){
	$(".bigPicture").animate({width:'0%',height:'0%'},1000);
	setTimeout(function(){
		$(this).hide();
	},1000);
});

$(".uploadResult").on("click","span",function(e){
	var targetFile = $(this).data("file");
	var type = $(this).data("type");
	console.log(targetFile);
	
	$.ajax({
		url:'/deleteFile',
		data:{fileName: targetFile, type:type},
		dataType:'text',
		type:'post',
		success:function(result,status,xhr){
			alert(result);
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
 	<div class="uploadResult">
 		<button id="uploadBtn">Upload</button>
		 	<ul>
		 	</ul>
 	</div>
 	<div class="bigPictureWrapper">
 		<div class="bigPicture"></div>
 	</div>
 	
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