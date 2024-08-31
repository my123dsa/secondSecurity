<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
	<a href="/demo_war_exploded/index.html">홈으로</a>
	<table>
		<c:choose>
			<c:when test="${empty requestScope.list || fn:length(list) == 0}">
				<tr>
					<td>등록된 Mouse 정보가 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${requestScope.list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.professorName}</td>
						<td>${item.credit}</td>
						<td>${item.currentCount}</td>
						<td>${item.capacity}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</table>
	<form action="/demo_war_exploded/page/user/search" method="post">
    <input type="text" name="search" placeholder="검색어를 입력하세요">
    <button type="submit">제출</button>
    </form>
    <div>
    </div>
	<form action="/demo_war_exploded/page/user/courseselect" method="post">
    <input type="number" name="courseId" placeholder="Enter your courseId">
    <button type="submit">제출</button>
	</form>
	
</body>
</html>
