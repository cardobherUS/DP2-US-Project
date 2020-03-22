<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <jsp:body>

    <h2>New Diet for <c:out value="${client.firstName} ${client.lastName}"/></h2>

	<h3>Diet Data</h3>

	<form:form modelAttribute="diet" id="dietForm">
        <fmt:message var="name" key="name"/>
        <fmt:message var="description" key="description"/>
        <fmt:message var="type" key="type"/>

        <yogogym:inputField label="${name}" name="name"/>
        <yogogym:inputField label="${description}" name="description"/>
        <yogogym:selectField label="${type}" name="type" names="${dietTypes}" size="1"/>

		<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit"><fmt:message key="saveDiet"/></button>
            </div>
        </div>
		
	</form:form>

    </jsp:body>
</yogogym:layout>