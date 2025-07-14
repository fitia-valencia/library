<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Connexion Adhérent - Bibliothèque</title>
</head>
<body>
    <h2>Connexion Adhérent</h2>

    <form action="${pageContext.request.contextPath}/adherent-login" method="post">
        <label>Matricule :</label>
        <input type="text" name="matricule" value="ADH2025RJ2" required /><br/>

        <label>Mot de passe :</label>
        <input type="password" name="motDePasse" value="jean" required /><br/>

        <input type="submit" value="Se connecter" />
    </form>
    <p>Vous n'avez pas encore de compte? <a href="${pageContext.request.contextPath}/adherent-register">S'inscrire</a></p>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>
    
    <br>
    <a href="/">← Retour au choix de connexion</a>

</body>
</html>
