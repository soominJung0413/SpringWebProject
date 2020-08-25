<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> -->
		
		
		<%@include file="../includes/header.jsp" %>
		<script type="text/javascript">
			$(document).ready(function(){
				var result = '<c:out value='${result}' />';
				
				 checkModal(result);
				
				window.history.replaceState({}, null, null);
				
				
				 function checkModal(result){
					if(result == '' || history.state){
						return;
					}
					
					console.log(result);
					
					if(parseInt(result) > 0 ){
						$('.modal-body').html('게시글 '+parseInt(result) + ' 번이 등록되었습니다.');
					}
					if(result === 'modifysuccess'){//내가만든것
						$('.modal-body').html('게시글 수정이 완료되었습니다.');
					}
					if(result === 'removesuccess'){//내가만든것
						$('.modal-body').html('게시글 삭제가 완료되었습니다.');
					}
					
					$('#myModal').modal('show');
				} 
				 
				 $('#regBtn').on('click',function(){
					 window.location.href = '/board/register';
				 });
				
			});
		</script>
	
           <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        
                        Board List Page
                        <button type="button" id="regBtn" class="btn btn-xs pull-right">Register New Board</button>
                         </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table  class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                
                                <c:forEach items="${list}" var="board">
                                	<tr>
                                		<td><c:out value="${board.bno}" /></td>
                                		<td><a href="/board/get?bno=<c:out value='${board.bno }' />">
                                		<c:out value="${board.title }" />
                                		</a>
                                		</td>
                                		<td><c:out value="${board.writer}" /></td>
                                		<td><fmt:formatDate pattern="yyyy-MM-dd"
                                		 value="${board.regdate }" /></td>
                                		 <td><fmt:formatDate  pattern="yyyy-MM-dd"
                                		 value="${board.updateDate }"/></td>
                                	</tr>
                                </c:forEach>
                                
                            </table>
                            
                             <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
                            aria-labelledby="myModalLabel" aria-hidden="true">
                            	<div class="modal-dialog">
                            		<div class="modal-content">
                            			<div class="modal-header">
                            				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">
                            					&times;
                            				</button>
                            				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            			</div>
                            			<div class="modal-body">처리가 완료되었습니다.</div>
                            			<div class="modal-footer">
                            				<button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
                            				<button class="btn btn-primary" type="button">Save Changes</button>
                            			</div>
                            		</div>
                            		<!-- /.modal-content -->
                            	</div>
                            	<!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->                      
                           
                            
                        </div>
                        <!-- End Panel Body -->
                  	</div>
            		<!-- End Panel -->
            	</div>
            </div>
             <!-- /.row -->
            
            <%@include file="../includes/footer.jsp" %>
       