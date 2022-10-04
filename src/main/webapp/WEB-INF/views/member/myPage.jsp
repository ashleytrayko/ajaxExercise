<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
<h1 align="center">회원정보수정</h1>
<div class="">
	<form action="/member/modify.kh" method="post">
		<table>
			<tr>
				<td> * 아이디</td>
				<td>
					<input type="text" id="memberId" name="memberId" value="${member.memberId }" readonly>
				</td>
			</tr>
			<tr>
				<td> * 비밀번호</td>
				<td>
					<input type="password" name="memberPwd" value="" >
				</td>
			</tr>
			<tr>
				<td> * 이름</td>
				<td>
					<input type="text" name="memberName" value="${member.memberName }" readonly >
				</td>
			</tr>
				<tr>
					<td> * 이메일</td>
					<td>
						<input type="text" name="memberEmail" value="${member.memberEmail }" >
					</td>
				</tr>	
				<tr>
					<td> * 전화번호</td>
					<td>
						<input type="text" name="memberPhone" value="${member.memberPhone }" >
					</td>
				</tr>
				<tr>
					<td>  우편번호</td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" value="${addrInfos[0] }" >
						<button type="button" id="postcodify_search_button">검색</button>
					</td>
				</tr>
				<tr>
					<td> 도로명 주소</td>
					<td>
						<input type="text" name="address1" class="postcodify_address"  value="${addrInfos[1] }" >
					</td>
				</tr>
					<tr>
					<td> 상세 주소</td>
					<td>
						<input type="text" name="address2" class="postcodify_details"  value="${addrInfos[2] }" >
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">
						<button type="button" onclick="removeMember();">탈퇴하기</button> <!-- 이벤트 전달을 막기위해 type=button써주기 -->
					</td>
				</tr>
			</table>
		</form>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
		<script>
			function removeMember(){
				if(confirm("탈퇴하시겠습니까?")){
					location.href="/member/remove.kh";
				}
			}
			$("#postcodify_search_button").postcodifyPopUp();
		</script>
</body>
</html>