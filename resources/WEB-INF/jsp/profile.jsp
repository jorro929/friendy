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
    <form method="post" action="/profile" enctype="multipart/form-data">


        <input type="hidden" name="_method" value="PUT">
        <input type="text" name="id" hidden value="${profile.id}">

        <table>

            <tr>
                <td><h3>Email</h3></td>
                <td>
                    <input type="email" name="email" hidden value="${profile.email}">
                    <a href="/credentials?id=${profile.id}">${profile.email}</a>
                </td>
            </tr>

            <tr>
                <td><h3>${wordBundle.getWord("name")}</h3></td>
                <td><input type="text" name="name" value="${profile.name}"></td>
            </tr>

            <tr>
                <td><h3>${wordBundle.getWord("surname")}</h3></td>
                <td><input type="text" name="surname" value="${profile.surname}"></td>
            </tr>

            <tr>
                <td><h3>${wordBundle.getWord("birth-date")}</h3></td>
                <td><input type="date" name="date-birth" value="${profile.birthDate}"></td>
            </tr>

            <tr>
                <td><h3>${wordBundle.getWord("age")}</h3></td>
                <td><h3>${requestScope.profile.age}</h3></td>
            </tr>


            <tr>
                <td><h3>${wordBundle.getWord("about")}</h3></td>
                <td><input type="text" name="about" value="${requestScope.profile.about}"></td>
            </tr>
            <tr>
                <td><h3>${wordBundle.getWord("gender")}</h3></td>
                <td>
                    <select name="gender">
                        <option value="${requestScope.profile.gender}" selected hidden>
                            ${requestScope.wordBundle.getWord(requestScope.profile.gender)}
                        </option>
                        <c:forEach var="gender" items="${applicationScope.genders}">
                            <option value="${gender}">${requestScope.wordBundle.getWord(gender)}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td><h3>${wordBundle.getWord("photo")}</h3></td>
                <td>
                    <c:if test="${profile.photo != null}">
                        <img src="content/profiles/${profile.photo}" alt="" height="300">
                        <br>
                    </c:if>
                    <input type="button" value="${wordBundle.getWord('update')}" onclick="document.getElementById('file').click();" />
                    <input type="file" name="photo" id="file" style="display:none;">
                </td>
            </tr>
        </table>

        <button type="submit">${wordBundle.getWord("save")}</button>
    </form>


    <form method="post" action="/profile">
        <c:if test="${profile.id != null}">
            <input type="hidden" name="_method" value="DELETE">
            <input type="hidden" name="id" value="${requestScope.profile.id}">
            <button type="submit">${requestScope.wordBundle.getWord("delete")}</button>
        </c:if>
    </form>

    <div style="color: red">
        <tr>
            <td>
                <h3>
                    ${errors}
                </h3>
            </td>
        </tr>

    </div>

</div>

<%@ include file="footer.jsp" %>

</body>
