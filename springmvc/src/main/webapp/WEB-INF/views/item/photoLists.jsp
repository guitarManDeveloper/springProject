<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
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
<br/>
<body >
<div class='container'>
    <div class='container'>
        <%@ include file="/WEB-INF/views/common/loginCommon.jsp" %>
    </div>
    <br/>
    <h2 class="text-center">상품 목록 게시판</h2>
    <br/>
    <c:set var="buttonType1" value="primary"/>
    <c:set var="buttonType2" value="primary"/>
    <c:if test="${itemVO.viewType eq 'list'}">
        <c:set var="buttonType1" value="danger"/>
    </c:if>
    <c:if test="${itemVO.viewType eq 'photo'}">
        <c:set var="buttonType2" value="danger"/>
    </c:if>
    <a href="./selectItemList?viewType=list" class="btn btn-outline-${buttonType1}" >리스트형</a>
    <a href="./selectItemList?viewType=photo" class="btn btn-outline-${buttonType2}" >카드형</a>
    <div class="row">
        <form action="./selectItemList" method="get">
            <div class="input-group" style="float:right; width: 400px; margin-bottom: 15px;">
                <input type="hidden" value="${itemVO.viewType}" name="viewType"/>
                <input type="search" name="itemName" class="form-control rounded" placeholder="상품명을 입력하세요." value="${itemVO.itemName}" aria-label="Search" aria-describedby="search-addon" />
                <button type="submit" class="btn btn-outline-primary">검색</button>

            </div>
        </form>


        <c:set var="number" value="${totalCnt-offset}"/>
        <c:forEach var="item" items="${itemList}">
            <div class="col-3" style="margin-top: 15px;">
                <div class="card" >
                    <img class="card-img-top" style="width: 304px; height: 304px;" src="/resources/userImageData/${item.originalFileName}" alt="" />
                    <div class="card-body">
                        <h5 class="card-title">${item.itemName}</h5>
                        <p class="card-text"><fmt:formatNumber value="${item.price }" pattern="#,###"/>원</p>
                        <a href="./detailItem?id=${item.id}&page=${itemVO.page}&viewType=${itemVO.viewType}" class="btn btn-primary btn-sm">상세보기</a>
                    </div>
                </div>
            </div>
            <c:set var="number" value="${number-1}"/>
        </c:forEach>
    </div>

    <br/>

    <a href="./createItem?page=${itemVO.page}&viewType=${itemVO.viewType}" class="btn btn-primary border-right btn-sm" style="float: right">상품등록</a>
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item ${currentPage == 1? 'disabled' : '' }">
                <a class="page-link" href="?page=${currentPage-1}">이전</a>
            </li>
            <c:forEach begin="1" end="${totalPage}" var="pageNumber">
                <li class="page-item ${pageNumber == currentPage ? 'active' : '' }" aria-current="page">
                    <a class="page-link" href="?page=${pageNumber}&viewType=${itemVO.viewType}">${pageNumber}</a>
                </li>
            </c:forEach>
            <li class="page-item ${currentPage == totalPage? 'disabled' : '' }">
                <a class="page-link" href="?page=${currentPage+1}">다음</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>