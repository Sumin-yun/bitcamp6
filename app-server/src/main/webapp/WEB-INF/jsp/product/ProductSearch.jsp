<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

  .search-img {
  
    width: 20px;
  }
  
  .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
  .wrap * {padding: 0;margin: 0;}
  .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
  .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
  .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
  .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
  .info .close:hover {cursor: pointer;}
  .info .body {position: relative;overflow: hidden;}
  .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
  .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
  .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
  .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
  .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
  .info .link {color: #5085BB;}


</style>
<br>
<br>
<br>
<br>
<h1>상품 검색</h1>
<br>

   

<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:400px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e95526ee94a73ad7b5fda1653496933"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.49874549019091, 127.02854048591665), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 마커를 표시합니다 
/* var marker = new kakao.maps.Marker({
    map: map, 
    position: new kakao.maps.LatLng(37.49874549019091, 127.02854048591665)
});
 */

var positions = [
    {
        title: '와인창고', 
        latlng: new kakao.maps.LatLng(37.49874549019091, 127.02854048591665)
    },
    {
        title: '세계주류', 
        latlng: new kakao.maps.LatLng(37.498564, 127.030838)
    }
];

 
 for(let i=0; i < positions.length; i++){
	    var data = positions[i];
	    displayMarker(data);
	}
 

//지도에 마커를 표시하는 함수입니다    
function displayMarker(data) { 
  var marker = new kakao.maps.Marker({
      map: map,
      position: data.latlng
  });
  var overlay = new kakao.maps.CustomOverlay({
      yAnchor: 3,
      position: marker.getPosition()
  });
  
   var content = document.createElement('div');
  content.innerHTML =  data.title; 
  content.style.cssText = 'background: white; border: 1px solid black';  

  var closeBtn = document.createElement('button');
  closeBtn.innerHTML = '닫기';
  closeBtn.onclick = function () {
      overlay.setMap(null);
  };
  content.appendChild(closeBtn);
  overlay.setContent(content);

  kakao.maps.event.addListener(marker, 'click', function() {
      overlay.setMap(map);
  });
}

/* // 커스텀 오버레이에 표시할 컨텐츠 입니다
// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
// 별도의 이벤트 메소드를 제공하지 않습니다 
var content = '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class="title">' + 
            '            와인창고' + 
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="img">' +
            '                <img src="${contextRoot}/image/alcohol2.jpg" width="73" height="70">' +
            '           </div>' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">서울특별시 강남구 역삼동 882-1 </div>' + 
            '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' +  
            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';

            
// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
var overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: marker.getPosition()       
}
); */

/* // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
kakao.maps.event.addListener(marker, 'click', function() {
    overlay.setMap(map);
});


// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
    overlay.setMap(null);     
} */

</script>








