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
    <form method="post" action="/registration">

        <table>
            <td><input type="email" name="email" hidden value="${email}"></td>
            <td><input type="text" name="password" hidden value="${password}"></td>

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

            <tr>
                <td><h3>${requestScope.wordBundle.getWord("about")}</h3></td>
                <td><input type="text" name="about" value="${requestScope.profile.about}"></td>
            </tr>


            <tr>
                <td><h3>${requestScope.wordBundle.getWord("gender")}</h3></td>
                <td>
                    <select name="gender">
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
</div>

<%@ include file="footer.jsp" %>

</body>