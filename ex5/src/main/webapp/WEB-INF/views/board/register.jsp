<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../includes/header.jsp"%>

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
	
	<%@include file="../includes/footer.jsp" %>