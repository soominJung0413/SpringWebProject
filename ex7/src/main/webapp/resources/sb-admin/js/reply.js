 
  var replyService = (function(){
		
		function add(reply,callback, error){
			console.log("reply,,,,,,,,,,,,,,,,,,,,");
			
			$.ajax({
				type:'post',
				url:'/replies/new',
				data:JSON.stringify(reply),
				contentType:'application/json; charset=utf-8',
				success : function(result, status, xhr){
					if(callback){
					callback(result);
					}
				},
				error : function(xhr, status, er){
				if(error){
					error(er);
				}
				}
			});
		}
		
		function getList(param , callback , error){
		
		var bno = param.bno;
		var page = param.page||1;
		
		$.getJSON("/replies/pages/"+bno+"/"+page+".json",
		function(data){
			if(callback){
				callback(data);
			}
			
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
		}
		
		function remove(rno, callback, error){
			$.ajax({
				type:'delete',
				url:'/replies/'+rno,
				success : function(deleteResult , status, xhr){
				if(callback){
				 callback(deleteResult);
				}
				},
				error : function(xhr , status , err){
				if(error){
					error(err);
				}
				}
			});
		}
		
		function update(reply , callback, error){
		 	console.log(reply.rno);
		 	$.ajax({
		 		type:'put',
		 		url:'/replies/'+reply.rno,
		 		data: JSON.stringify(reply),
		 		contentType : 'application/json; charset=utf-8',
		 		success : function(result, status, xhr){
		 			if(callback){
		 				callback(result);
		 			}
		 		},error : function(xhr, status, err){
		 			if(error){
		 				error(err);
		 			}
		 		}
		 	});
		}
		
		function get(rno, callback, error){
			$.get('/replies/'+rno+'.json',function(result){
				if(callback){
					callback(result);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error(err);
				}
			});
		}
		
		function displayTime(timeValue){
			var today= new Date();
			
			var gap = today.getTime() - timeValue;
			
			var timeObj = new Date(timeValue);
			
			var str = "";
			
			if( gap < (1000 * 60 * 60 *24) ) {
				var hh = timeObj.getHours();
				var mi = timeObj.getMinutes();
				var ss = timeObj.getSeconds();
				
				return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0')+ mi, ':', (ss > 9 ? '' : '0')+ss ].join('');
			} else {
				var yy = timeObj.getFullYear();
				var mm = timeObj.getMonth() + 1;
				var dd = timeObj.getDate();
				
				return [ yy, '/', (mm > 9 ? '' : '0')+ mm, '/', (dd > 9 ? '' : '0') + dd ].join('');
			}
		
		}
		
	return {add:add,
				 getList: getList,
				 remove: remove,
				 update : update,
				 get : get,
				 displayTime : displayTime
				 };
	})();

 console.log("Reply Module......");