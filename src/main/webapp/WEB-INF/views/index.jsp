<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Se connecter en tant que</h2>

    <a href="${pageContext.request.contextPath}/bibliothecaire-login">Bibliothecaire</a>
    <a href="${pageContext.request.contextPath}/adherent-login">Adhérent</a>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>
</body>
</html>
