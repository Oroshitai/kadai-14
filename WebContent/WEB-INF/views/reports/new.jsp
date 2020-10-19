<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<h2>日報　新規登録ページ</h2>

		<form method="POST" name="form_new" action="">
			<c:import url="_form.jsp" />

		</form>

		<script>
			function funcClick(url){
				document.form_new.action = url;
				document.form_new.submit();
			}
		</script>

		<p><a href="<c:url value='/reports/index ' />">一覧に戻る</a></p>
	</c:param>
</c:import>