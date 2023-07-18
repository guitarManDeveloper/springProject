<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
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
	})
</script>

<body>
<br/>
<div class='container'>
	<div class='container'>
        <%@ include file="/WEB-INF/views/common/loginCommon.jsp" %>
	</div>
	<br/>
	<h2 class="text-center">상품 등록</h2>
	<br/>
	<form method="post" enctype="multipart/form-data" action="./createItem">
		<table class="table table-bordered">
			<colgroup>
				<col width="180px">
				<col>
			</colgroup>
			<tr>
				<th>종류</th>
				<td><select id="commonCodeSelect" name="f_id" style="width: 150px"  class="form-select" aria-label="Default select example"></select></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" style="width: 250px" class="form-control" id="itemname" name="item_name" value="" placeholder="상품이름을 입력하세요"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" style="width: 250px"  class="form-control" id="price" name="price" value="" placeholder="상품가격을 입력하세요"></td>
			</tr>
            <tr>
                <th>이미지</th>
                <td><input type="file" style="width: 250px"  class="form-control" id="image" name="image" value="" placeholder="이미지를 첨부하세요"></td>
            </tr>
		</table>
		<button id='btn_modify' type="submit" class="btn btn-primary btn-sm" style="float: right;margin-left: 5px">등록</button>
		<a href="./selectItemList?page=${pageNumber}&viewType=${searchMap.viewType}" class="btn btn-secondary btn-sm" style="float: right;margin-left: 5px">목록</a>
	</form>
</div>
</body>
</html>