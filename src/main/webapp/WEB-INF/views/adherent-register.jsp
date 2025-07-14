<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><c:choose>
        <c:when test="${depuisAjout}">Création Adhérent - Bibliothèque</c:when>
        <c:otherwise>Inscription Adhérent - Bibliothèque</c:otherwise>
    </c:choose></title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${depuisAjout}">Création d'un nouvel adhérent</c:when>
            <c:otherwise>Inscription d’un nouvel adhérent</c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/adherent-register" method="post">
    <input type="hidden" name="depuisAjout" value="${depuisAjout}" />
        <label>Nom :</label>
        <input type="text" name="nom" required /><br/>

        <label>Prénom :</label>
        <input type="text" name="prenom" required /><br/>

        <label>Date de naissance :</label>
        <input type="date" name="dateNaissance" required /><br/>

        <label>Téléphone :</label>
        <input type="text" name="telephone" required /><br/>

        <label>Mot de passe :</label>
        <input type="password" name="motDePasse" required /><br/>

        <label>Type d’adhérent :</label>
        <select name="typeAdherent.id" required>
            <c:forEach var="type" items="${typesAdherent}">
                <option value="${type.id}">${type.libelle}</option>
            </c:forEach>
        </select><br/>

        <input type="submit" 
               value="<c:choose><c:when test='${depuisAjout}'>Créer</c:when><c:otherwise>S’inscrire</c:otherwise></c:choose>" />
    </form>

    <c:if test="${not depuisAjout}">
        <p>Vous avez déjà un compte ? 
            <a href="${pageContext.request.contextPath}/adherent-login">Se connecter</a>
            <br>
            <a href="/">← Retour au choix de connexion</a>
        </p>
    </c:if>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>

    <c:if test="${depuisAjout}">
        <br>
        <a href="/dashboard">← Retour au dashboard</a>
    </c:if>
</body>
</html>
