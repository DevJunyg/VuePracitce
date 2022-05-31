
var checkService = (function(){

	function email_chk(){

		let email = $("input[name=email]").val().trim();
				
		//이메일 형식 정규식 
		const regxp_email = /^[0-9a-zA-Z]([0-9a-zA-Z]|[_])*[@]{1}[a-z]*[.][a-z]{1,3}$/;
		const regxp_msg = /^[0-9a-zA-Z~!@#$%^&*()_+-=|<>?:{}]+$/;
		//한글 금지 정규식
		const regxp_kor = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]$/;
			
		$("input[name=email]").keyup(function(e){
			
			email = $("input[name=email]").val().trim();
	
			//엔터키 방지용
			if(regxp_msg.test(email) || e.keyCode==8){				
				if(email.trim()==""){
					$("#email_msg").text("필수 입력 항목 입니다.").css("color","red");
					$("input[name=email]").css("border", "1px solid red");
				}else if(email.trim()!="" && e.keyCode!=13){
					$("#email_msg").text("");
					$("input[name=email]").css("border", "1px solid black");
				}
			}
		});
		
		//이메일 형식인지 판별
		if(email == ""){
			$("#email_msg").text("필수 입력 항목 입니다.").css("color", "red");
			$("input[name=email]").css("border", "1px solid red");
			return false;
		}
		else if(regxp_kor.test(email)==true){
			$("#email_msg").text("영숫자로 입력해주세요.").css("color","red");
			$("input[name=email]").css("border", "1px solid red");
			return false;
		}else if(regxp_email.test(email) == false){
			$("#email_msg").text("올바른 이메일 형식이 아닙니다.").css("color","red");
			$("input[name=email]").css("border", "1px solid red");
			return false;
		}else if(email.length < 20){
			$("#email_msg").text("이메일을 20자리이상 입력해 주세요.").css("color","red");
			$("input[name=email]").css("border", "1px solid red");
			return false;
		}else{
			let rtn=false;
			$.ajax({
				url:"/vuelog/email_chk",
				type:"post",
				data:{
					email:email
				},
				async:false,
				success:function(data){
					if(data == "1"){
						$("#email_msg").html("이미 존재하는 이메일입니다.").css("color","red");
						$("input[name=email]").css("border", "1px solid red");
					}else if(data == "0"){
						$("#email_msg").text("사용가능한 이메일입니다.").css("color","blue");
						$("input[name=email]").css("border", "1px solid black");
						rtn=true;
					}
				},error:function(data){
					console.log(data.email);
				}
			}); 
			return rtn;
		}
	}

	function id_chk(){

		let result = false;
		
		const regxp_id = /^[0-9a-zA-Z]+$/;
		const regxp_msg = /^[0-9a-zA-Z~!@#$%^&*()_+-=]+$/;
		const regxp_kor = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]$/;
		
		let id = $("input[name=id]").val().trim();

		$("input[name=id]").keyup(function(e){

			id = $("input[name=id]").val().trim();
			
			//엔터키 방지용
			if(regxp_msg.test(id) || e.keyCode==8){				
				if(id=="" || id.trim()==""){
					$("#id_msg").text("필수 입력 항목 입니다.").css("color","red");
					$("input[name=id]").css("border", "1px solid red");
				}else if(id!="" && e.keyCode!=13){
					$("#id_msg").text("");
					$("input[name=id]").css("border", "1px solid black");
				}
			}
		});
	
		if(id==""){
			$("#id_msg").text("필수 입력 항목 입니다.").css("color","red");
			$("input[name=id]").css("border", "1px solid red");
		}else if(regxp_kor.test(id)==true){
			$("#id_msg").text("영숫자로 입력해주세요.").css("color","red");
			$("input[name=id]").css("border", "1px solid red");
		}else if(id.length < 8){
			$("#id_msg").text("아이디를 8자리이상 입력해 주세요.").css("color","red");
			$("input[name=id]").css("border", "1px solid red");
		}else{
			$.ajax({
				url:"/vuelog/id_chk",
				type:'post',
				data:{
					id:id
				},
				async:false,
				success:function(data){
					if(data == "1"){
						$("#id_msg").html("이미 존재하는 아이디입니다.").css("color","red");
						$("input[name=id]").css("border", "1px solid red");
					}else if(data == "0"){
						$("#id_msg").text("사용가능한 아이디입니다.").css("color","blue");
						$("input[name=id]").css("border", "1px solid black");
						result = true;
					}
				}
			}); 
			return result;
		}
	}
		
	function password_chk(){
		
		//패스워드 길이 정규식 (숫자,대소문자,특수문자 사용가능 최소8자리)
		const regxp_password = /^([a-zA-Z0-9!@#$%^&*()_+]{8,})$/;
		const regxp_num_search = /([0-9])/g;
		const regxp_str_search = /([a-zA-Z])/g;
		const regxp_msg = /^[0-9a-zA-Z~!@#$%^&*()_+-=]+$/;
		
		let password = $("input[name=password]").val();
		let result = false;
		
		
		//패스워드 입력값 없을시
		if(password==""){
			$(password_msg).text("필수 입력 항목 입니다.").css("color","red");
			$("input[name=password]").css("border","1px solid red");
		}else if(password != password.replace(/(\s+)/g)){
			$(password_msg).text("공백을 제외한 나머지를 입력해 주세요.").css("color","red");
			$("input[name=password]").css("border","1px solid red");
		}else if(!regxp_password.test(password)){
			if(password.length<8){

				$(password_msg).text("8자리 이상 입력해 주세요.").css("color","red");
				$("input[name=password]").css("border","1px solid red");
			}
		}else if(!password.match(regxp_num_search)){
			$("#password_msg").text("숫자와 대소문자를 하나 이상 입력해주세요.").css("color","red");
			$("input[name=password]").css("border","1px solid red");
		}else if(!password.match(regxp_str_search)){
 			$("#password_msg").text("숫자와 대소문자를 하나 이상 입력해주세요.").css("color","red");
			$("input[name=password]").css("border","1px solid red");
		}else{
			$("#password_msg").text("");
			$("input[name=password]").css("border","1px solid black");
			result = true;
		}
		$("input[name=password]").keyup(function(e){
			password = $("input[name=password]").val(); 
		
			if(e.keyCode==8){
				if(password==""){
					$(password_msg).text("필수 입력 항목 입니다.").css("color","red");
					$("input[name=password]").css("border","1px solid red");
				}
			}else if(password !="" && e.keyCode!=13){
				$(password_msg).text("");
				$("input[name=password]").css("border","1px solid black");
			}
		}); 
		return result;	
	}
	
	function repassword_chk(){
		
		let result = false;
	
		const password = $("input[name=password]").val();
		const regxp_msg = /^[0-9a-zA-Z~!@#$%^&*()_+-=]+$/;
		
		let repassword = $("input[name=repassword]").val();	
		
		$("input[name=repassword]").keyup(function(e){

			repassword = $("input[name=repassword]").val();

			//엔터키 방지용
			if(regxp_msg.test(repassword) || e.keyCode==8){				
				if(repassword=="" || repassword.trim()==""){
					$("#repassword_msg").text("필수 입력 항목 입니다.").css("color","red");
					$("input[name=repassword]").css("border", "1px solid red");
				}else if(repassword!="" && e.keyCode!=13){
					$("#repassword_msg").text("");
					$("input[name=repassword]").css("border", "1px solid black");
				}
			}
		});
		
		//패스워드 길이 정규식
		if(repassword==""){
			$("#repassword_msg").text("필수 입력 항목입니다").css("color","red");
			$("input[name=repassword]").css("border", "1px solid red");
		}else if(password==""){
			if(repassword != repassword.replace(/(\s*)/g,"")){
				$("#repassword_msg").text("공백문자를 제외한 숫자,대소문자,특수문자 중 입력해주세요.").css("color","red");
			}	
			$("#repassword_msg").text("비밀번호를 입력해 주세요.").css("color","red");
			$("input[name=repassword]").css("border", "1px solid red");
		}else if(password != repassword){
			$("#repassword_msg").text("비밀번호가 일치하지 않습니다.").css("color","red");
			$("input[name=repassword]").css("border", "1px solid red");
		}else if(password == repassword){
			$("#repassword_msg").text("비밀번호가 일치합니다.").css("color","blue");
			$("input[name=repassword]").css("border", "1px solid red");
			result = true;
		}
		return true;
	}
	
	function nick_chk(){
	
		let result = false;
		
		const regxp_nickname = /^[0-9a-zA-Z]+$/;
		const regxp_msg = /^[0-9a-zA-Z~!@#$%^&*()_+-=]+$/;
		const regxp_kor = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]$/;
		
		let nickname = $("input[name=nickname]").val().trim();

		$("input[name=nickname]").keyup(function(e){
	
			nickname = $("input[name=nickname]").val().trim();
			
			//엔터키 방지용
			if(regxp_msg.test(nickname) || e.keyCode==8){				
				if(nickname=="" || nickname.trim()==""){
					$("#nickname_msg").text("필수 입력 항목 입니다.").css("color","red");
					$("input[name=nickname]").css("border", "1px solid red");
				}else if(nickname!="" && e.keyCode!=13){
					$("#nickname_msg").text("");
					$("input[name=nickname]").css("border", "1px solid black");
				}
			}
		});
	
		if(nickname==""){
			$("#nickname_msg").text("필수 입력 항목 입니다.").css("color","red");
			$("input[name=nickname]").css("border", "1px solid red");
		}else if(regxp_kor.test(nickname)==true){
			$("#nickname_msg").text("영숫자로 입력해주세요.").css("color","red");
			$("input[name=nickname]").css("border", "1px solid red");
		}else if(nickname.length < 8){
			$("#nickname_msg").text("닉네임을 8자리이상 입력해 주세요.").css("color","red");
			$("input[name=nickname]").css("border", "1px solid red");
		}
		$.ajax({
			url:"/vuelog/nickname_chk",
			type:'post',
			data:{
				nickname:nickname
			},
			async:false,
			success:function(data){
				if(data == "1"){
					$("#nickname_msg").text("이미 사용중인 닉네임입니다.").css("color","blue");
					$("input[name=nickname]").css("border", "1px solid red");
				}else if(data == "0"){
					$("#nickname_msg").text("사용가능한 닉네임입니다.").css("color","blue");
					$("input[name=nickname]").css("border", "1px solid black");
					result = true;
				}
			}
		}); 
		return result;
	}
	
	return{
		email_chk:email_chk,
		id_chk:id_chk,
		password_chk:password_chk,
		repassword_chk:repassword_chk,
		nick_chk:nick_chk 
	};

})();