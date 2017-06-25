<%@ attribute name="field" required="true" rtexprvalue="false" %>
<%@ attribute name="list" required="true" rtexprvalue="true" type="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach items="${list}" var="e">
	<c:if test="${e.field eq field }">
		${e.message }<br>
	</c:if>
</c:forEach>