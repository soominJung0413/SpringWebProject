-<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../includes/header.jsp"%>

<style>
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
<script type="text/javascript">
		$(document).ready(function(){
			
			var formObj = $("form[role='form']");
			
			$("button[type='submit']").on("click",function(e){
				e.preventDefault();
				
				console.log("submit clicked")
			});
			
			$("input[type='file']").on("change",function(e){
				var formData = new FormData();// 가상의 폼
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;
				
				for(var i =0; i<files.length; i++){
					
					if( ! checkExtention( files[i].name, files[i].size ) ){
						return false;
					}
					formData.append("uploadFile", files[i]);
				}//인풋 파일에 0 번쨰 인덱스에서 files 를 꺼내오면파일 목록명단 객체임 거기서 확장자 및 사이즈 체크, 통과한다면 formData에 append 시킴
				
				 $.ajax({
					url:'/uploadAjaxAction',
					processData: false,
					contentType:false,
					data: formData,//FormData 객체를 그냥 넘겨줘도 된다!
					type:'post',
					dataType:'json',
					success:function(result){
						console.log(result);
						//업로드 결과 피드백 함수 추가필요
						showUploadResult(result);
					}
				});// Ajax 
			});
			
			// 파일 확장자 및 사이즈 검토
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880;
			
			function checkExtention(fileName, fileSize){
				
				if( fileSize >= maxSize ){
					alert("사이즈 초과");
					return false;
				}
				if( regex.test(fileName) ){
					alert("해당 종류의 파일은 업로드 할 수 없습니다.");
					return false;
				}
				return true;
			}
			// 파일 확장자 및 사이즈 검토 종료
			
			//섬네일 및 아이콘생성
			function showUploadResult(uploadResultArr){
				if( !uploadResultArr || uploadResultArr.length ==0 ){
					return;
				}
				
				var uploadUL = $(".uploadResult ul");
				
				var str ="";
				$(uploadResultArr).each(function(i, obj){
					if(obj.fileType){
						var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
						
						str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-fileName='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
						str += "<span> "+obj.fileName+"</span>";
						str += "<button type='button' data-file='"+fileCallPath+"' data-type='image' class='btn btn-outline-warning btn-circle'><i class='fa fa=times'></i></button><br>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div>";
						str += "</li>";
					}else{
						var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
						var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
						
						str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-fileName='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
						str += "<span>"+obj.fileName+"</span>";
						str += "<button type='button' data-file='"+fileCallPath+"' data-type='file' class='btn btn-warning btn-circle' ><i class='fa fa-times'></i></button><br>";
						str += "<img src='/resources/img/attach.jpg'></a></div></li>";
					}
				});
				
				uploadUL.append(str);
			}
			
			$(".uploadResult").on("click","button",function(e){
				console.log("delete file");
				
				var targetFile = $(this).data("file");
				var type = $(this).data("type");
				
				var targetLi = $(this).closest("li");
				
				$.ajax({
					url:'/deleteFile',
					data:{ fileName:targetFile, type:type },
					dataType:'text',
					type:'post',
					success:function(result){
						alert(result);
						targetLi.remove();
					}
				});//ajax
			});
			
			//기존 폼전송 부분 파일 정보 히든태그 추가
			var formObj = $("form[role='form']");
			
			$("button[type='submit']").on("click",function(e){
				e.preventDefault();
				console.log("submit clicked");
				
				var str ="";
				$(".uploadResult ul li").each( function(i, obj){
					var jobj = $(obj);
					
					console.dir(jobj);
					
					str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'> ";
					console.log(str);
					str += "<input type='hidden' name='attachList["+i+"].uuid'  value='"+jobj.data("uuid")+"'> ";
					str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
					str += "<input type='hidden' name='attatchList["+i+"].fileType' value='"+jobj.data("type")+"'>";
				});
				formObj.append(str).submit();
				
				
			});
			
		});
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Register</h1>
	</div>
	<!-- !--/.col-lg-12  -->
</div>
	<!--  !--/.row  -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Register</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form action="/board/register" method="post" role="form">
						<div class="form-group">
							<label >Title</label>
								<input type="text" class="form-control" name="title" />
						</div>
						<div class="form-group">
							<label >Text area</label>
							<textarea name="content"class="form-control" rows="3"></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" class="form-control" name="writer" />
						</div>
						<button class="btn btn-default" type="submit">Submit Button</button>
						<button class="btn btn-default" type="reset">Reset Button</button>
					</form>
				</div>
				<!-- end panel body -->
			</div>
			<!-- end panel-->
		</div>
		<!-- end col -->
	</div>
	<!-- end row -->
	
	<!-- 파일 업로드 부분 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				
				<div class="panel-heading">File Attach</div>
				<!-- ./panel heading -->
				<div class="panel-body">
					<div class="form-group uploadDiv">
						<input type="file" name="uploadFile"  multiple />
					</div>
					
					<div class="uploadResult">
						<ul>
							
						</ul>
					</div>
				</div>
				<!-- end panel body -->
			</div>
			<!-- end panel-default -->
		</div>
		<!-- end col-lg-12 -->
	</div>
	<!-- end row -->
	
	<%@include file="../includes/footer.jsp" %>