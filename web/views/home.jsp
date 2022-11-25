<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1> Hello, <c:out value="${currentUser.firstname}"></c:out></h1>
<br>

<div>
    <p>Write Your Post</p>
    <form method="post" action="wall">
        <textarea name="postText"></textarea><br>
        <input type="submit" value="Post">
    </form>
    <h3><c:out value="${postAdded}"></c:out></h3>
</div>

<c:forEach items="${posts}" var="post">
    <div>
        <p>${post.post}</p>
        <form method="post" action="like">
            <input type="hidden" name="id" value="${post.id}"/>
            <input type="hidden" name="operation" value="like"/>
            <input type="submit" value="Like">
        </form>
        <form method="post" action="like">
            <input type="hidden" name="id" value="${post.id}"/>
            <input type="hidden" name="operation" value="unlike"/>
            <input type="submit" value="Unlike">
        </form>
        <h3>count = <c:out value="${post.likesCount}"></c:out></h3>
        <c:forEach items="${post.comments}" var="comment">
            <h4>${comment.commentText}</h4>

        </c:forEach>
    </div>
    <hr>
</c:forEach>


</body>
</html>
