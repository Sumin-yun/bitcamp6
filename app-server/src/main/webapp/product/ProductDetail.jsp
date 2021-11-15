<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<style>
a {
    text-decoration: none;
    color: black;
}
button {
  float: right;
}

</style>

 <h1>ìí ì ë³´ìì </h1>
 <a href="../upload/product/${product.photo}">
<img id="f-photo-image" src="../upload/product/${product.photo}_1000x1000.jpg" 
        align="left" width="300px" height="700px" >
        </a>
<form action='update' method='post' enctype="multipart/form-data">
    <input type='hidden' id='f-productNumber' type='text' name='productNumber' class="form-control" value='${product.productNumber}' readonly>
    
<div class="mb-3 row">        
<div class="mb-3 row">
  <label for='f-name' class="col-sm-2 col-form-label">ìíëª</label>
  <div class="col-sm-6">
    <input id='f-name' type='text' name='name' class="form-control" value='${product.productName}' readonly>
  </div>
</div>
<%--     <div class="mb-3 row">
  <label for='f-photo' class="col-sm-2 col-form-label">ì¬ì§</label>
  <div class="col-sm-6">
    <input id='f-photo' type='text' name='photo' class="form-control" value='${product.photo}' readonly>
  </div>
</div> --%>
<div class="mb-3 row">
  <label for='f-photo' class="col-sm-2 col-form-label">ì¬ì§</label>
  <div class="col-sm-6">
<img id="f-photo-image" src="../upload/product/${product.photo}_1000x1000.jpg" width="100px" height="100px">
    <input id='f-photo' type='file' name='photo' class="form-control" >
  </div>
</div>
    <div class="mb-3 row">
  <label for='f-rate' class="col-sm-2 col-form-label">íì </label>
  <div class="col-sm-6">
    <input id='f-rate' type='text' name='rate' class="form-control" value='${product.rate}' readonly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-type' class="col-sm-2 col-form-label">ì£¼ì¢</label>
  <div class="col-sm-6">
    <input id='f-type' type='text' name='type' class="form-control" value='${product.productType.type}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-subType' class="col-sm-2 col-form-label">ìì¸ì£¼ì¢</label>
  <div class="col-sm-6">
    <input id='f-subType' type='text' name='subType' class="form-control" value='${product.productType.subType}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-countryOrigin' class="col-sm-2 col-form-label">ìì°ì§</label>
  <div class="col-sm-6">
    <input id='f-countryOrigin' type='text' name='countryOrigin' class="form-control" value='${product.countryOrigin}'>
  </div>
</div>
<c:choose> 
  <c:when test="${product.productType.type eq 'ìì¸'}">
    <div class="mb-3 row">
      <label for='f-variety' class="col-sm-2 col-form-label">íì¢</label>
      <div class="col-sm-6">
        <input id='f-variety' type='text' name='variety' class="form-control" value='${product.variety}' >
      </div>
    </div>
  </c:when>
</c:choose>

<div class="mb-3 row">
  <label for='f-volume' class="col-sm-2 col-form-label">ì©ë</label>
  <div class="col-sm-6">
    <input id='f-volume' type='text' name='volume' class="form-control" value='${product.volume}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-alcoholLevel' class="col-sm-2 col-form-label">ëì</label>
  <div class="col-sm-6">
    <input id='f-alcoholLevel' type='text' name='alcoholLevel' class="form-control" value='${product.alcoholLevel}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-sugarLevel' class="col-sm-2 col-form-label">ë¹ë</label>
  <div class="col-sm-6">
    <input id='f-sugarLevel' type='text' name='sugarLevel' class="form-control" value='${product.sugarLevel}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-acidity' class="col-sm-2 col-form-label">ì°ë</label>
  <div class="col-sm-6">
    <input id='f-acidity' type='text' name='acidity' class="form-control" value='${product.acidity}' >
  </div>
</div>
<div class="mb-3 row">
  <label for='f-weight' class="col-sm-2 col-form-label">ë°ëê°</label>
  <div class="col-sm-6">
    <input id='f-weight' type='text' name='weight' class="form-control" value='${product.weight}' >
  </div>
</div>
</div> 



  <button type="button" onclick="location.href='list'" class="btn btn-outline-success">ëª©ë¡</button>
  <button type="button" onclick="location.href='delete?no=${product.productNumber}'" class="btn btn-outline-success">ì­ì </button>
  <button class="btn btn-outline-success">ë³ê²½</button>
</form>
