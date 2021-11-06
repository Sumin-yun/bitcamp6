<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새예약</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>새예약</h1>
<form action='add'>
<div class="mb-3 row">
  <label for='f-cartNumber'>장바구니번호</label> 
  <div class="col-sm-6">
    <input id='f-cartNumber' type='text' name='cartNumber' value='${cartNo}'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-id'>구매자</label> 
  <div class="col-sm-6">
    <input id='f-id' type='text' name='id' value='${id}'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-bookingDate'>예약날짜</label>
  <div class="col-sm-6">
    <input id='f-bookingDate' type='date' name='bookingDate'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-bookingTime'>예약시간</label>
  <div class="col-sm-6">
    <input id='f-bookingTime' type="time" name='bookingTime'><br>
    <p>1.카드결제 / 2.실시간 계좌이체 / 3.무통장입금 / 4.휴대폰 결제 / 5.현장결제</p>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-paymentType'>결제방법</label>
  <div class="col-sm-6">
    <input id='f-paymentType' type="text" name='paymentType'><br>
  </div>
</div>
<button class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!-- .container -->
</body>
</html>