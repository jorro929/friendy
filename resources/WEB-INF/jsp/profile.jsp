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
    <form method="post" action="/profile">

        <c:if test="${requestScope.profile.id != null}">
            <input type="hidden" name="_method" value="PUT">
        </c:if>

        <table>
            <td><input type="text" name="id" hidden value="${requestScope.profile.id}"></td>
            <td><input type="text" name="status" hidden value="${requestScope.profile.status}"></td>

            <tr>
                <td><h3>Email</h3></td>
                <td>
                    <input type="email" name="email" hidden value="${requestScope.profile.email}">
                    <a href="/email?id=${requestScope.profile.id}">${requestScope.profile.email}</a>
                </td>
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
                <td><h3>${requestScope.wordBundle.getWord("birth-date")}</h3></td>
                <td><input type="date" name="date-birth"  value="${requestScope.profile.birthDate}"></td>
            </tr>

            <c:if test="${requestScope.profile.id != null}">
                <tr>
                    <td><h3>${requestScope.wordBundle.getWord("age")}</h3></td>
                    <td><h3>${requestScope.profile.age}</h3></td>
                </tr>
            </c:if>

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("about")}</h3></td>
                <td><input type="text" name="about" value="${requestScope.profile.about}"></td>
            </tr>
            <tr>
                <td><h3>${requestScope.wordBundle.getWord("gender")}</h3></td>
                <td>
                    <select name="gender">
<!--                        ${requestScope.profile.gender}-->
<!--                        -->
                        <option value="${requestScope.profile.gender}" selected hidden>${requestScope.wordBundle.getWord(requestScope.profile.gender)}</option>
                        <c:forEach var="gender" items="${applicationScope.genders}">
                            <option value="${gender}">${requestScope.wordBundle.getWord(gender)}</option>
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
