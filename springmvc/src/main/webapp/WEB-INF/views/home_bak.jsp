<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.min.js" ></script>

<script type="text/javascript">
	function buttonClick() {
		$.ajax({
			url:"/comcode",
			type:'POST',
			dataType:"json",
			success:function(data){
				var options = "";
				for(var i = 0; i< data.length; i++){
					options+="<option value='" + data[i].id + "'>" +data[i].description + "</option>" 
				}
				$("#commonCodeSelect").html(options);
			},
			error:function(){
				alert("error load common code");
			}			
		});
	}
	
	function buttonClick2() {
		window.location.href = 'http://localhost:8080/selectItemList';
	}
	function buttonClick3() {
		window.location.href = 'http://localhost:8080/create';
	}
	$(document).ready(function(){		
			/* $.ajax({
				url:"/comcode",
				type:'POST',
				dataType:"json",
				success:function(data){
					var options = "";
					for(var i = 0; i< data.length; i++){
						options+="<option value='" + data[i].id + "'>" +data[i].description + "</option>" 
					}
					$("#commonCodeSelect").html(options);
				},
				error:function(){
					alert("error load common code");
				}			
			}); */
	})
</script>

<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<select id="commonCodeSelect"></select>
<button onclick="buttonClick()">click me...</button>
<button onclick="buttonClick2()">시작하기</button>
<button onclick="buttonClick3()">제품등록하기</button>
</body>
</html>
