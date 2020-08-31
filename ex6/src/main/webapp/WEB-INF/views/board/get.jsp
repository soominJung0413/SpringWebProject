<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../includes/header.jsp"%>

	<script type="text/javascript">
		$(document).ready(function(){
			
			var operForm = $('#operForm');
			
			$('button[data-oper=modify]').on('click',function(e){
				operForm.attr('action','/board/modify').submit();
			});
			
			$('button[data-oper=list]').on('click',function(e){
				operForm.find('#bno').remove();
				operForm.attr('action','/board/list');
				operForm.submit();
			});
			
		});
	</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- !--/.col-lg-12  -->
</div>
	<!--  !--/.row  -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Read Page</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
						<div class="form-group">
							<label >Bno</label>
								<input type="text" class="form-control" name="bno" value="<c:out value='${board.bno}' />" readonly="readonly" />
						</div>
						
						<div class="form-group">
							<label >Title</label>
								<input type="text" class="form-control" name="title" value="<c:out value='${board.title}' />" readonly="readonly" />
						</div>
						
						<div class="form-group">
							<label >Test area</label>
								<textarea class="form-control"  rows="3" name="content" readonly="readonly"><c:out value="${board.content}" /></textarea>
						</div>
						
						<div class="form-group">
							<label >Writer</label>
								<input type="text" class="form-control" name="writer" value="<c:out value='${board.writer}' />" readonly="readonly" />
						</div>
						
						<button class="btn btn-default" data-oper='modify' >Modify</button>
						<button class="btn btn-info" data-oper='list' >List</button>
						
						<form action="/board/modify" method="get" id="operForm">
							<input type="hidden" name="bno" id="bno" value="<c:out value='${board.bno }' />" />
							<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}' />" />
							<input type="hidden" name="amount" value="<c:out value='${cri.amount}' />" />
								<input type="hidden" name="keyword" value="<c:out value="${cri.keyword}" />"/>
                            	<input type="hidden" name="type" value="<c:out value="${cri.type}" />"/>
						</form>
					
				</div>
				<!-- end panel body -->
			</div>
			<!-- end panel-->
		</div>
		<!-- end col -->
	</div>
	<!-- end row -->
	
	<%@include file="../includes/footer.jsp" %>