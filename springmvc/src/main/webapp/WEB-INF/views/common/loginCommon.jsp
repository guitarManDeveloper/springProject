<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){

        $("#logout").click(function(){
            let confirmation = confirm("로그아웃 하시겠습니까?");
            if(!confirmation){
                return false;
            }
            $.ajax({
                url: '/logout',
                type:'POST',
                success:function(response){
                    window.location.href = './selectItemList';
                },
                error:function(){
                }
            });
        });
    });

    function fn_deleteAt(url) {
        if( confirm("삭제하시겠습니까?")){
            window.location = url;
        }
        return false;
    }
</script>
<c:if test="${empty loginVO}"> 
    <a href="./login" id='login' class='btn btn-secondary btn-sm' style="float: right;margin-left: 10px">사용자로그인</a>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=596030f6cbaa088976c54efe745729dc&redirect_uri=http://localhost:8080/kakaoLogin&response_type=code" id='login' style="float: right"> <img src="/resources/snsLogin/kakao_login_small.png"/></a>
</c:if>

<c:if test="${not empty loginVO}">
    <button id='logout' class = 'btn btn-success btn-sm' style="float: right; margin-left: 10px">로그아웃</button>
    <div style="float: right"><strong>${loginVO.userName}</strong></div>
</c:if>