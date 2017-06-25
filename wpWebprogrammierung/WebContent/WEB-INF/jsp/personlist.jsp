<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="de.fom.wp.persistence.Gender"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="my" tagdir="/WEB-INF/tags" %> 


 <my:base>
	<jsp:attribute name="title">
	Person List
	</jsp:attribute>
	<jsp:attribute name="headline">
	Meine Super Personen Liste
	</jsp:attribute>
	<jsp:body> 
		<table class="table table-hover">
		<thead>
			<tr>
				<th class="hidden-xs"><fmt:message key="i18n.gender"/></th>
				<th><fmt:message key="i18n.firstname"/></th>
				<th><fmt:message key="i18n.lastname"/></th>
				<th><fmt:message key="i18n.email"/></th>
				<th class="hidden-xs"><fmt:message key="i18n.birthday"/></th>
				<th class="hidden-xs"><fmt:message key="i18n.height"/></th>
				<th class="hidden-xs"><fmt:message key="i18n.newsletter"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personlist}" var="p">
				<tr>
					<td class="hidden-xs"><img src="<c:url value="/images/${p.gender.icon}"/>"></td>
					<td>${p.firstname}</td>
					<td>${p.lastname}</td>
					<td><a href="<c:url value="/register.html?id=${p.id}"/>">${p.email}</a></td>
					<td class="hidden-xs"><fmt:formatDate value="${p.birthday}" pattern="${datepattern}"/></td>
					<td class="hidden-xs"><fmt:formatNumber value="${p.height}"/></td>
					<td class="hidden-xs">
						<c:if test="${p.newsletter}">
							<img src="<c:url value="/images/checkbox_unchecked.png"/>">
						</c:if>
						<c:if test="${!p.newsletter}">
							<img src="<c:url value="/images/checkbox.png"/>">
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
 	</jsp:body>
</my:base>	