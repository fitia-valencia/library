<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Connexion Bibliothecaire - Bibliothèque</title>
</head>
<body>
    <h2>Connexion Bibliothecaire</h2>

    <form action="${pageContext.request.contextPath}/bibliothecaire-login" method="post">
        <label>Matricule :</label>
        <input type="text" name="matricule" value="BIB001" required /><br/>

        <label>Mot de passe :</label>
        <input type="password" name="motDePasse" value="biblio123" required /><br/>

        <input type="submit" value="Se connecter" />
    </form>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>

    <br>
    <a href="/">← Retour au choix de connexion</a>
    
</body>
</html>
