<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../includes/header.jsp"%>

<!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                            	<label>Reply</label>
                                            	<input type="text" name="reply" value="NewReply!!!" class="form-control" />
                                            </div>
                                             <div class="form-group">
                                            	<label>Replyer</label>
                                            	<input type="text" name="replyer" value="replyer" class="form-control" />
                                            </div>
                                            <div class="form-group">
                                            	<label>Reply Date</label>
                                            	<input type="text" name="replyDate" value="" class="form-control" />
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" id="modalModBtn" class="btn btn-warning">Modify</button>
                                            <button type="button" id="modalRemoveBtn" class="btn btn-danger">Remove</button>
                                             <button type="button" id="modalRegisterBtn" class="btn btn-primary" >Register</button>
                                             <button type="button" id="modalCloseBtn" class="btn btn-default" data-dismiss="modal">Close </button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->

<script type="text/javascript" src="/resources/sb-admin/js/reply.js"></script>
	
	<script type="text/javascript">
		console.log("=================");
		console.log("JS TEST");
		
		var bnoValue = '<c:out value="${board.bno}" />'; 
		
		//for replyService Test
		/* replyService.add(
		{reply:"JS Test" , replyer : "tester" , bno: bnoValue},
		function(result){
			alert("RESULT : "+result);
		}
		); */
		
		//for getList Test
		/* replyService.getList({bno:bnoValue, page:1}, function(list){
			for(var i =0, len = list.length||0; i<len; i++){
				console.log(list[i]);
			} */
			
			//for remove Test
			/* replyService.remove(21,function(count){
				console.log(count);
				if( count === 'Success') {
					alert('REMOVED');
				}
			}, function(err){
				alert('ERROR......');
			}); */
			
			//for update Test
			/* replyService.update({
			rno : 7,
			bno : bnoValue,
			reply : "Modify Reply....."
			}, function(result) {
				alert('수정 완료');
			}); */
			
			//for get Test
		/* 	replyService.get(7, function(result) {
				console.log(result);
			}); */
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		

		var bnoValue = '<c:out value="${board.bno}" />';
		var replyUL = $('.chat');
		
		showList(1);
		
		function showList(page){
			console.log("show list "+page);
		
			replyService.getList({bno:bnoValue, page: page||1}, function(replyCnt, list) {
				console.log("Reply Count :"+replyCnt);
				console.log("list :"+list);
				console.log(list);
				
				if(page == -1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				var str = "";
				
				if(list == null || list.length == 0){
					replyUL.html("");
					return ;
					}
				
				for( var i = 0 , len= list.length; i<len; i++ ){
					str += "<li class='left chearfix' data-rno='"+list[i].rno+"'> ";
					str += " <div><div class='header' ><strong class='primary-font' > ["+list[i].rno+"] "+list[i].replyer+"</strong>";
					str += " <small class='pull-right test muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
					str += " <p>"+list[i].reply+"</p></div></li>";
				}
				
				replyUL.html(str);
				
				showReplyPage(replyCnt,page);
				});/* end function */
				
			};/* end showList */
			
			var modal = $('.modal');
			var modalInputReply = modal.find("input[name='reply']");
			var modalInputReplyer = modal.find("input[name='replyer']");
			var modalInputReplyDate = modal.find("input[name='replyDate']");
			
			var modalModBtn = $('#modalModBtn');
			var modalRemoveBtn = $('#modalRemoveBtn');
			var modalRegisterBtn = $('#modalRegisterBtn');
			
			$('#addReplyBtn').on('click',function(e){
					modal.find('input').val("");
					modalInputReplyDate.closest('div').hide();
					modal.find("button[id !='modalCloseBtn']").hide();
					
					modalRegisterBtn.show();
					
					$('.modal').modal('show');
			});
			
			modalRegisterBtn.on("click",function(e){
			
				var reply = {
						reply : modalInputReply.val(),
						replyer:modalInputReplyer.val(),
						bno: bnoValue
						};
				replyService.add(reply, function(result){
					alert(result);
					
					modal.find("input").val("");
					modal.modal("hide");
					
					showList(-1);
				});
				
			});
			
			$(".chat").on("click","li",function(e){
				
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply) {
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
				modal.data("rno",reply.rno);
				
				modal.find("button[id !=modalCloseBtn]").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			});
			});
			
			modalModBtn.on("click",function(e){
				
				var reply = {
						rno : modal.data("rno"),
						reply : modalInputReply.val()
						};
				
				replyService.update(reply, function(result) {
						alert(result);
						modal.modal("hide");
						
						var pageNum = $('.panel-footer').find("li.active a").attr('href');
						
						showList(pageNum);
				});
			});
			
			modalRemoveBtn.on("click",function(e){
				
				var rno = modal.data("rno");
				
				replyService.remove(rno, function(result) {
					
					alert(result);
					modal.modal("hide");
					
					var pageNum = $('.panel-footer').find("li.active a").attr('href');
					
					showList(pageNum);
				});
			});
			
			$('.panel-footer').on("click","li a",function(e){
				
				e.preventDefault();
				var targetPageNum = $(this).attr("href");
				
				console.log("target PageNum : "+targetPageNum);
				
				var pageNum = targetPageNum;
				
				showList(pageNum);
			});
			
			
		});
	</script>
	
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
	
	 <script type="text/javascript">
		 var pageNum = 1;
		
		
		function showReplyPage(replyCnt,pageNum){
			
		console.log(pageNum);
			var endNum = Math.ceil(pageNum / 10.0) *10;
			var startNum = endNum - 9;
			
			var prev = startNum !=  1;
			var next = false;
			
			if( endNum * 10 >= replyCnt ){
				endNum = Math.ceil(replyCnt/10.0);
			}
			if( endNum * 10 < replyCnt ){
				next = true;
			}
			
			var str = "<ul class='pagination pull-right' >";
			if (prev) {
				str += "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
			}
			
			for(var i =startNum ; i<= endNum ; i++){
				var active = pageNum == i ? "active":"";
				
				str += "<li class='page-item "+active+" '> <a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
			
			if(next){
				str += "<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li> ";
			}
			
			str += "</ul></div>";
			
			console.log(str);
			
			$('.panel-footer').html(str); 
		}
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
	<!-- reply row  -->
	<div class="row">
		<div class="col-lg-12">
			<!-- /.panel  -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
				</div>
				<!-- /.panel-heading  -->
				<div class="panel-body">
					
					<ul class="chat">
						<li class="left clearfix" data-rno='12'>
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong>
									<small class="pull-right text-muted">2018-01-01 13:13</small>
								</div>
								<p>Good Job!</p>
							</div>
						</li>
						<!-- end reply -->
					</ul>
					<!-- ./end ul -->
				</div>
				<!-- ./end panel .chat-panel  -->
				<!-- ./ panel footer -->
				<div class="panel-footer">
				</div>
			</div>
		</div>
	</div>
		<!-- end row  -->
	
	<%@include file="../includes/footer.jsp" %>