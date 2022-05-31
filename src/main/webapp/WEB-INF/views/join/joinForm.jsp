<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<style>
input {
	outline:0;
}
</style>
<!-- jquery 호출 -->
<script type="text/javascript" src="${contextPath }/resources/js/jQuery/3.5.1/jquery-3.5.1.js"></script>
<script src="${contextPath }/resources/js/join/check.js"></script>
<!-- 유효성 -->
<script>
$(document).ready(function(){
	const csrfHeaderName = "${_csrf.headerName}";
	const csrfTokenValue= "${_csrf.token}";
	const form = document.joinFrm;
	
	$(document).ajaxSend(function(e,xhr,options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});
	
	//회원가입 버튼 클릭시 유효성검사 실행
	$("input[type=submit]").click(function(){
		console.log("click");
		

		function join_chk(){
			
			let result = false;
			let email_result = false;
			let id_result = false;
			let password_result = false;
			let repassword_result = false;
			let nickname_result = false;
			
			if(checkService.email_chk()){
				email_result=true;
			}

			if(checkService.id_chk()){
				id_result=true;
			}
		
			if(checkService.password_chk()){
				password_result=true;
			}
		
			if(checkService.repassword_chk()){
				repassword_result=true;
			}
		
			if(checkService.nick_chk()){
				nickname_result=true;
			}
		
			
			if(email_result == true && id_result == true && password_result == true && repassword_result == true && nickname_result){	
				console.log("true인가?");
				$("#email_msg").text("");
				$("input[name=email]").css("border", "1px solid black");
				
				$("#id_msg").text("");
				$("input[name=id]").css("border", "1px solid black");
				
				$("#password_msg").text("");
				$("input[name=password]").css("border", "1px solid black");
				
				$("#repassword_msg").text("");
				$("input[name=repassword]").css("border", "1px solid black");
				
				$("#nickname_msg").text("");
				$("input[name=nickname]").css("border", "1px solid black");
				result = true;	
			}
			return result;
		};
		
		if(join_chk()==true){
			const id = $("input[name=id]").val();
			const password = $("input[name=password]").val();
			const email = $("input[name=email]").val();
			const nickname = $("input[name=nickname]").val();
			location.reload();
			
			$.ajax({
				url:"/vuelog/join/joinOk",
				type:"post",
				data:{
					email:email,
					id:id,
					password:password,
					nickname:nickname
				},
				async:false,
				success:function(data){
					if(data=="1"){
						location.href="/vuelog/index";
					}else{
						location.href="/vuelog/join_fail";
					}
				}
			}); 
		}
	});
});
</script>
<body>
<form name="joinFrm"  method="get" onsubmit="return false;">
	<div>
		이메일<input type="text" id="email" name="email" >
		<span id="email_msg"></span><br>
	</div>
	<div>
		아이디<input type="text" id="id" name="id">
		<span id="id_msg"></span><br>
	</div>
	<div>
		비밀번호<input type="password" id="password" name="password">
		<span id="password_msg"></span><br>
	</div>
	<div>
		비밀번호 확인<input type="password" id="repassword" name="repassword">
		<span id="repassword_msg"></span><br>
	</div>
	<div>
		닉네임<input type="text" id="nickname" name="nickname">
		<span id="nickname_msg"></span><br>
	</div>
	<div>
		<input type="submit" value="회원가입">
	</div>
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
</form>
</body>
</html>

