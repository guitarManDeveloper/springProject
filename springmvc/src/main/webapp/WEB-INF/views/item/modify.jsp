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
		
		$("#btn_modify").click(function(event){
			event.preventDefault(); // 기본 폼 제출 동작 중지
			let confirmation = confirm("수정하시겠습니까?");
			if(!confirmation){
				return false;  // 폼 전송 취소
			}
			let formData = $('form').serialize(); // 폼 데이터를 직렬화
			
			$.ajax({
				url: '/modifyItem',
				type:'POST',
				data:formData,
				success:function(response){
					alert("수정되었습니다.");
					window.location.href = 'http://localhost:8080/selectItemList';
				},
				error:function(){
					alert("업데이트 하지 못했습니다. \n\n계속되면 관리자에게 문의하세요");
				}			
			});			
		});
		
		/* 삭제 ajax */
		$("#btn_delete").click(function(event){
			event.preventDefault(); // 기본 폼 제출 동작 중지
			let confirmation = confirm("삭제하시겠습니까?");
			if(!confirmation){
				return false;  // 폼 전송 취소
			}
			let formData = $('form').serialize(); // 폼 데이터를 직렬화
			
			$.ajax({
				url: '/deleteItem',
				type:'POST',
				data:formData,
				success:function(response){
					alert("삭제되었습니다.");
					window.location.href = 'http://localhost:8080/selectItemList';
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
                    if (code[i].id === ${data.f_id}) {
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
    <h2 class="text-center">상품 수정</h2>
    <br/>
	<form action="./modifyItem" method="post">
        <input type="hidden" name="id" value="${data.id}">
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
                <td><input type="text" style="width: 150px" class="form-control" id="itemname" name="item_name" value="${data.item_name}" placeholder="상품이름을 입력하세요"></td>
            </tr>
            <tr>
                <th>가격</th>
                <td><input type="number" style="width: 150px"  class="form-control" id="price" name="price" value="${data.price }" placeholder="상품가격을 입력하세요"></td>
            </tr>
        </table>
		<button id='btn_modify' class="btn btn-primary btn-sm" style="float: right;margin-left: 5px">수정</button>
		<button id='btn_delete' class="btn btn-danger btn-sm" style="float: right;margin-left: 5px">삭제</button>
        <a href="./selectItemList?page=${pageNumber}&viewType=${searchMap.viewType}" class="btn btn-secondary btn-sm" style="float: right;margin-left: 5px">목록</a>
	</form>
</div>
</body>
</html>