<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 12/2/22
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choose page</title>
</head>
<body>
<h1>choose from pages</h1>


<ul>
    <c:forEach items="${pages}" var="pageName">
        <li><h4><a href="page?choosepage=${pageName}"><c:out value="${pageName}"/></a> </h4>
    </c:forEach></ul>
</body>
</html>
