<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retour d'exemplaire</title>
</head>
<body>
    <h2>Retour d'un exemplaire</h2>

    <c:if test="${not empty emprunt}">
        <p><strong>Nom de l'adhérent :</strong> ${emprunt.adherent.nom} ${emprunt.adherent.prenom}</p>
        <p><strong>Livre :</strong> ${emprunt.exemplaire.livre.titre} - Ref : ${emprunt.exemplaire.reference}</p>
        <p><strong>Date d'emprunt :</strong> ${emprunt.dateEmprunt}</p>
        <p><strong>Date retour prévue :</strong> ${emprunt.dateRetourPrevue}</p>

        <form action="retourner" method="post">
            <input type="hidden" name="empruntId" value="${emprunt.id}" />
            <label for="dateRetourReelle">Date de retour réelle :</label>
            <input type="date" name="dateRetourReelle" required />
            <button type="submit">Valider le retour</button>
        </form>
    </c:if>

    <c:if test="${empty emprunt}">
        <p>Emprunt introuvable.</p>
    </c:if>

</body>
</html>
