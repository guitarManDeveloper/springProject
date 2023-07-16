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
		$("#login").click(function(event){
			event.preventDefault(); // 기본 폼 제출 동작 중지			
			let formData = $('form').serialize(); // 폼 데이터를 직렬화
			
			$.ajax({
				url: '/login',
				type:'POST',
				data:formData,
				success:function(response){					
					window.location.href = './selectItemList';
				},
				error:function(){
					alert("로그인 실패. \n\n아이디와 패스워드를 확인해 주세요");
				}			
			});			
		});
	});
</script>

<body>
<div class='container'>
    <br/>
    <h2 class="text-center">로그인</h2>
    <br/>
    <form>
        <table class="table table-bordered" style="width: 800px; margin-left: auto; margin-right: auto;">
            <colgroup>
                <col width="180px">
                <col>
            </colgroup>
            <tr>
                <th>이메일</th>
                <td>
                    <input type="email" style="width: 250px;" class="form-control" name = 'email'>
                </td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td>
                    <input type="password" style="width: 250px;" class="form-control" name="user_psw">
                </td>
            </tr>
        </table>
        <button class="btn btn-primary" id="login" style="margin-left: 970px; margin-right: auto;">로그인</button>
    </form>
</div>
</body>
</html>