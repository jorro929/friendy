<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="UTF-8">
    <title>Friendy</title>
</head>
<%@ include file="style.html" %>
<body>
<%@ include file="header.jsp" %>
<div>
    <form method="post" action="/credentials?id=${profile.id}" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" name="id" value="${requestScope.profile.id}">
        <input type="hidden" name="profile" value="${requestScope.profile}">
        <table>
            <tr>
                <td><h3>${requestScope.wordBundle.getWord("email")}</h3></td>
                <td><input type="email" name="email" value="${requestScope.profile.email}"></td>
            </tr>
            <tr>
                <td><h3>${requestScope.wordBundle.getWord("password")}</h3></td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><h3>${requestScope.wordBundle.getWord("password.again")}</h3></td>
                <td><input type="password" name="password_again"></td>
            </tr>
        </table>
        <button type="submit">${requestScope.wordBundle.getWord("save")}</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
