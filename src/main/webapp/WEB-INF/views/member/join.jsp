<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
span.guide {
	display: none;
	font-size: 12px;
	top: 12px;
	right: 10px;
}

span.ok {
	color: green;
}

span.error {
	color: red;
}
</style>
</head>
<body>
	<h1 align="center">회원가입</h1>
	<div class="">
		<form action="/member/register.kh" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" id="memberId" name="memberId">
						<span class="guide ok">이 아이디는 사용 가능합니다.</span> <span
						class="guide error">이 아이디는 사용 할 수 없습니다.</span></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="memberPwd"></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="memberName"></td>
				</tr>
				<tr>
					<td>* 이메일</td>
					<td><input type="text" name="memberEmail"></td>
				</tr>
				<tr>
					<td>* 전화번호</td>
					<td><input type="text" name="memberPhone"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" name="post"
						class="postcodify_postcode5">
						<button type="button" id="postcodify_search_button">검색</button></td>
				</tr>
				<tr>
					<td>도로명 주소</td>
					<td><input type="text" name="address1"
						class="postcodify_address"></td>
				</tr>
				<tr>
					<td>상세 주소</td>
					<td><input type="text" name="address2"
						class="postcodify_details"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="가입하기"></td>
				</tr>
			</table>
		</form>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
		<script>
			$("#postcodify_search_button").postcodifyPopUp();
			$("#memberId").keyup(function(e) {
				var checkId = e.target.value;
				if(checkId !== ""){
					$(".ok").css("display", "none");
					$(".error").css("display", "none");
					$.ajax({
						url : "/member/checkId.kh",
						data : {
							"memberId" : checkId
						},
						type : "get",
						success : function(result) {
							if (result == "itsOk") {
								$(".ok").css("display","block");
							} else {
								$(".error").css("display","block");
							}
						},
						error : function() {
							alert("서버 통신 에러!");
						}
					})
					
				}else{
					$(".ok").css("display", "none");
					$(".error").css("display", "none");
				}

			})

			/* $("#memberId").on("blur",function(e) {
				var checkId = $("#memberId").val();
				$(".ok").css("display","none");
				$(".error").css("display","none");
				$.ajax({
					url : "/member/checkId2.kh",
					data : {"memberId" : checkId},
					type : "get",
					success : function(result) {
						if(result == "0"){
							$(".error").hide();
							$(".ok").show();
						}else{
							$(".ok").hide();
							$(".error").show();
						}
					},
					error : function() {
						alert("서버 통신 에러!");
					}
				})
			}) */
		</script>
</body>
</html>