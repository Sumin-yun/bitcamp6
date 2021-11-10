<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1> 브랜디 </h1>
<style>
td a {
    text-decoration: none;
    color: black;
}
td a:visited {
    color: black;
}
div.card:hover {
    cursor: pointer;
}
</style>
<form action='search' method='post'>  
<div class="mb-3 row">
  <label for='f-search' class="col-sm-1 col-form-label">검색</label>
    <div class="col-sm-2">
    <input id='f-search' type='text' name='search' class="form-control">
  </div>
</div>
</form>
 <c:set var="i" value="0" />
 <c:set var="j" value="4" />
 <table class="table table-hover">
  <c:choose>
   <c:when test="${productList ne null}">
    <c:forEach items="${productList}" var="product">
     <c:if test="{i%j == 0}">
      <tr>
     </c:if>
     <td >
      <div class="card" style="width: 15rem; position: relative;" >
        <p><img align="middle" class = "image" src = "../image/${product.photo}.jpg" style="width:14rem; height:300px;"></p>
        <p><a href="detail?no=${product.productNumber}" class="list-group-item">${product.productName}</a></p>
        <p class="card-text"> - 평점: ${product.rate}점</p>
        <p class="card-text"> - 주종: ${product.productType.type}</p>
        <p class="card-text"> - 도수: ${product.alcoholLevel}</p>
        <p class="card-text"> - 용량: ${product.volume} </p>
      </div> 
    </td>
    <c:if test="${i%j == j-1}">
     <tr>
    </c:if> 
   <c:set var="i" value="${i+1}" />
    </c:forEach>
   </c:when>
  <c:otherwise>
   <tr>
    <td>존재하지 않습니다.</td>
   </tr>
  </c:otherwise>
  </c:choose>
 </table>
 <script>
 <!--td.card a  td.card div.card-->
document.querySelectorAll("table a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("table div"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>

