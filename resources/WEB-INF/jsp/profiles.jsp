<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>Friendy Profiles</title>
    <meta charset="UTF-8">
    <%@ include file="style.html" %>
</head>
<body>

<%@ include file="header.jsp" %>

<div>
    <table>
        <tr>
            <td><h3>id</h3></td>
            <td><h3>email</h3></td>
            <td><h3>${requestScope.wordBundle.getWord("name")}</h3></td>
            <td><h3>${requestScope.wordBundle.getWord("surname")}</h3></td>
            <td><h3>${requestScope.wordBundle.getWord("age")}</h3></td>
            <td><h3>${requestScope.wordBundle.getWord("link-to-profile")}</h3></td>
            <td><h3>${requestScope.wordBundle.getWord("profile.status")}</h3></td>
        </tr>
        <c:forEach var="profile" items="${requestScope.profiles}">
            <tr>
                <td><h3>${profile.id}</h3></td>
                <td><h3>${profile.email}</h3></td>
                <td><h3>${profile.name}</h3></td>
                <td><h3>${profile.surname}</h3></td>
                <td><h3>${profile.age}</h3></td>
                <td><a href="/profile?id=${profile.id}">${requestScope.wordBundle.getWord("profile")}</a></td>
                <td>
<!--                    <select name="status">-->
<!--                        <option value="${requestScope.profile.status}" selected hidden>${requestScope.wordBundle.getWord(profile.status)}</option>-->
<!--                        <c:forEach var="status" items="${applicationScope.status}">-->
<!--                            <option value="${status}">${requestScope.wordBundle.getWord(status)}</option>-->
<!--                        </c:forEach>-->
<!--                    </select>-->
                    <form action="/profile" method="post">
                        <input type="hidden" name="_method" value="put"/>
                        <input type="hidden" name="id" value="${profile.id}">
                        <select name="status">
                            <option value="${requestScope.profile.status}" selected hidden>
                                ${requestScope.wordBundle.getWord(profile.status)}
                            </option>
                            <c:forEach var="status" items="${applicationScope.status}">
                                <option value="${status}">${requestScope.wordBundle.getWord(status)}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">${requestScope.wordBundle.getWord("save")}</button>
                    </form>
                </td>


            </tr>
        </c:forEach>
    </table>
</div>


<%@ include file="footer.jsp" %>

</body>
