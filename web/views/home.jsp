<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1> Hello, <c:out value="${currentUser.firstname}"></c:out> </h1>
<br>

<div>
    <p>Write Your Post</p>
    <form method="post" action="wall" >
        <textarea name="postText"></textarea><br>
        <input type="submit" value="Post">
    </form>
    <h3><c:out value="${postAdded}"></c:out></h3>
</div>

<c:forEach items="${posts}"  var="post">
    <div>
        <p>${post.post}</p>

    </div>
    <hr>
</c:forEach>



</body>
</html>
