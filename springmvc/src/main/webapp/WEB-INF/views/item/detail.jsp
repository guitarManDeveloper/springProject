<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		/* 삭제 ajax */
		$("#btn_delete").click(function(event){
			event.preventDefault(); // 기본 폼 제출 동작 중지
			let confirmation = confirm("삭제하시겠습니까?");
			if(!confirmation){
				return false;  // 폼 전송 취소
			}

			$.ajax({
				url: '/deleteItem',
				type:'POST',
				data:{id:${item.id},viewType:'${itemVO.viewType}'},
				success:function(response){
					alert("삭제되었습니다.");
					if('${itemVO.viewType}' == 'photo'){
						window.location.href = './selectItemList?viewType=${itemVO.viewType}';
					}else{
						window.location.href = './selectItemList';
					}
				},
				error:function(){
					alert("삭제 실패했습니다. \n\n계속되면 관리자에게 문의하세요");
				}			
			});			
		});
		
		
		$.ajax({
			url:"/comcode",
			type:'POST',
			dataType:"json",
			success:function(code){
				var options = "";
				for(var i = 0; i< code.length; i++){
					options += "<option value='" + code[i].id + "'";
                    if (code[i].id === ${item.fId}) {
                        options += " selected";
                    }
                    options += ">" + code[i].description + "</option>"; 
				}
				$("#commonCodeSelect").html(options);
			},
			error:function(){
				alert("error load common code");
			}			
		});
	})
</script>


<body>
<br/>
<div class='container'>
	<div class='container'>
		<%@ include file="/WEB-INF/views/common/loginCommon.jsp" %>
	</div>
	<br/>
	<h2 class="text-center">상품 상세보기</h2>
	<br/>
	<table class="table table-bordered text-center" style="width: 800px; margin-left: auto; margin-right: auto;">
		<colgroup>
			<col width="180px">
			<col>
		</colgroup>
		<tr>
			<th>종류</th>
			<td>${item.description}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${item.itemName}</td>
		</tr>
		<tr>
			<th>가격</th>
			<td><fmt:formatNumber value="${item.price }" pattern="#,###"/>원</td>
		</tr>
        <tr>
            <th>이미지</th>
            <%--<td>/image/파랑.jpg</td>--%>
            <td>
                <img class="card-img-bottom d-block" src="/resources/userImageData/${item.originalFileName }" alt="Card image cap">
            </td>
        </tr>
	</table>
	<div style="margin-left: 900px; margin-right: auto;">
		<a href="./selectItemList?page=${itemVO.page}&viewType=${itemVO.viewType}" class="btn btn-secondary btn-sm" >목록</a>
		<a href="./modifyItem?id=${item.id}&page=${itemVO.page}&viewType=${itemVO.viewType}" class="btn btn-primary btn-sm" >수정</a>
		<button id='btn_delete' class="btn btn-danger btn-sm">삭제</button>
	</div>

</div>

</body>
</html>