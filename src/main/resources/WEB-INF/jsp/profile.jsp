<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="UTF-8">
    <title>Friendy</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div>
    <form method="post" action="/profile">

        <c:if test="${requestScope.profile.id != null}">
            <input type="hidden" name="_method" value="PUT">
        </c:if>

        <table>
            <td><input type="text" name="id" hidden value="${requestScope.profile.id}"></td>
            <tr>
                <td><h3>Email</h3></td>
                <td><input type="email" name="email" value="${requestScope.profile.email}"></td>
            </tr>

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("name")}</h3></td>
                <td><input type="text" name="name" value="${requestScope.profile.name}"></td>
            </tr>

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("surname")}</h3></td>
                <td><input type="text" name="surname" value="${requestScope.profile.surname}"></td>
            </tr>

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("about")}</h3></td>
                <td><input type="text" name="about" value="${requestScope.profile.about}"></td>
            </tr>
            <tr>
                <td><h3>${requestScope.wordBundle.getWord("gender")}</h3></td>
                <td>
                    <select name="gender">
                        <option value="${requestScope.profile.gender}" selected hidden>${requestScope.profile.gender}</option>
                        <c:forEach var="gender" items="${applicationScope.genders}">
                            <option value="${gender}">${gender}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>

        <button type="submit">${requestScope.wordBundle.getWord("save")}</button>
    </form>


    <form method="post" action="/profile">
        <c:if test="${requestScope.profile.id != null}">
            <input type="hidden" name="_method" value="DELETE">
            <input type="hidden" name="id" value="${requestScope.profile.id}">
            <button type="submit">${requestScope.wordBundle.getWord("delete")}</button>
        </c:if>
    </form>


</div>

<%@ include file="footer.jsp" %>

</body>
</html>