<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<h1>${board.boardNo }번 게시글 수정하기</h1>
	<br>
	<form action="/board/modify.kh?page=${page }" method="post" enctype="multipart/form-data">
		<!--<input type="hidden" name="page" value="${page}">  -->
		<input type="hidden" name="boardNo" value="${board.boardNo }">
		<input type="hidden" name="boardFileRename" value="${board.boardFileRename }">
		<input type="hidden" name="boardFilename" value="${board.boardFilename }">
		
		
		<table align="center" border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="boardTitle" value="${board.boardTitle }"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="boardWriter" value="${board.boardWriter }" readonly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="boardContents" rows="7" cols="30">${board.boardContents }</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="reloadFile">
					<a href="#">${board.boardFilename }</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<a href="/board/list.kh">목록으로</a>
					<a href="javascript:history.go(-1);">이전 페이지로</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>