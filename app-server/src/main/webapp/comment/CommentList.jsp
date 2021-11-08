<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>댓글</title>
  <link rel="stylesheet" href="../../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>댓글 목록(MVC + EL + JSTL)</h1>
<!-- <a href='form'>이전</a><br> -->
<a href='form' class="btn btn-outline-primary btn-sm">댓글 작성</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>댓글번호</th>
    <th>게시글번호</th>
    <th>아이디</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${commentList}" var="comment">
<tr>
    <td>${comment.commentNumber}</td>  
    <td><a href='../../board/detail?no=${comment.boardNumber}'>${comment.boardNumber}</a></td> 
    <td>${comment.writer.id}</td> 
    <td>${comment.content}</td> 
    <td>${comment.registrationDate}</td> 
</tr>
</c:forEach>

</tbody>
</table>
</div><!-- .container -->
</body>
</html>

