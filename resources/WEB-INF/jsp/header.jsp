<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <h3>Frindy C=====3</h3>

    <form action="/lang" method="post">

        <button name="lang" value="ru">ru</button>
        <button name="lang" value="en">en</button>
        <button name="lang" value="fr">fr</button>
    </form>
    <h3>${requestScope.wordBundle.getWord("header")} ${cookie["lang"].value}</h3>

    <hr>
</div>

