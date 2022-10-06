<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Api 샘플 화면</title>
</head>
<body>
	<h1>Api 샘플</h1>
	<h2>1. 다음(카카오) 주소찾기 API</h2>
	<input type="text" id="postCode" placeholder="우편번호">
	<input type="button" id="" onclick="addrSearch()" value="우편번호 찾기">
	<br>
	<input type="text" id="roadAddr" placeholder="도로명주소">
	<input type="text" id="jibunAddr" placeholder="지번주소">
	<input type="text" id="detailAddr" placeholder="상세주소">
	<h2>2. 네이버 지도</h2>
	<div id="map" style="width: 100%; height: 400px;"></div>

	<script type="text/javascript"
		src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=qme1zfkiy6&submodules=geocoder"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function addrSearch() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							document.querySelector("#postCode").value = data.zonecode;
							document.querySelector("#roadAddr").value = data.roadAddress;
							document.querySelector("#jibunAddr").value = data.jibunAddress;
						}
					}).open();
		}
		var mapOptions = {
			center : new naver.maps.LatLng(37.5678912, 126.9830897),
			zoom : 20,
			zoomControl : true,
			zoomControlOptions : {
				position : naver.maps.Position.TOP_RIGHT,
				style : naver.maps.ZoomControlStyle.SMALL
			}
		};

		var map = new naver.maps.Map('map', mapOptions); // 지도 생성

		var marker = new naver.maps.Marker({ // 마커 생성
			position : new naver.maps.LatLng(37.5678912, 126.9830897), // 옵션설정
			map : map
		});
		var contentStr = "<div><h3>kh정보교육원</h3><p>서울시 중구 남대문로 120 대일빌딩 3F</p></div>";
		var infoWindow = new naver.maps.InfoWindow({ // 정보창 생성
			content : ""
		});
		infoWindow.open(map, marker);
		
		naver.maps.Event.addListener(marker, "click", function(e){ // 마커에 이벤트 연결
			if(infoWindow.getMap()){
				infoWindow.close(); // 정보창 닫기
			}else{
				infoWindow = new naver.maps.InfoWindow({
					content : contentStr
				});
				infoWindow.open(map, marker);
			}
		})
		
		naver.maps.Event.addListener(map,"click",function(e){
			marker.setPosition(e.coord);
			if(infoWindow.getMap()){
				infoWindow.close(); // 정보창 닫기
			}else{
				infoWindow.open(map, marker);
			}
			naver.maps.Service.reverseGeocode({
				location : new naver.maps.LatLng(e.coord.lat(), e.coord.lng())
			}, function(status, response){
				var data = response.result;
				var items = data.items;
				var address = items[1].address;
				contentStr = "<div><p>" + address + '</p></div>';
				console.log(contentStr);
			});
		});
	</script>
</body>
</html>