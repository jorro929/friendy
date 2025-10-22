<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="UTF-8">
    <title>Friendy</title>
</head>
<body>

<%@ include file="header.jsp" %>
<%@ include file="style.html" %>

<div>
    <form method="get" action="/registration">

        <table>
            <tr>
                <td><h3>Email</h3></td>
                <td><input type="email" name="email" value="${requestScope.profile.email}"></td>
            </tr>

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("password")}</h3></td>
                <td><input type="text" name="password" value="${requestScope.profile.password}"></td>
            </tr>

        </table>

        <button type="submit">${requestScope.wordBundle.getWord("continue")}</button>
    </form>
</div>

<%@ include file="footer.jsp" %>

</body>
