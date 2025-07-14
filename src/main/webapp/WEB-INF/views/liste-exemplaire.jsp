<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Exemplaires</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 2rem;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 2rem;
            background-color: white;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f0f8ff;
        }
    </style>
</head>
<body>
<a href="logout">Se déconnecter</a>
<h1>Liste des Exemplaires</h1>
        <a href="${pageContext.request.contextPath}/ajout-exemplaire">+ Ajouter un exemplaire</a>
        <h2>Filtrer les exemplaires</h2>

<form action="liste-exemplaire" method="get">
    <label for="livreId">Livre :</label>
    <select name="livreId">
        <option value="">-- Tous les livres --</option>
        <c:forEach var="livre" items="${livres}">
            <option value="${livre.id}"
                <c:if test="${param.livreId == livre.id}">selected</c:if>>
                ${livre.titre}
            </option>
        </c:forEach>
    </select>


    <label for="disponibilite">Disponibilité :</label>
    <select name="disponibilite">
        <option value="">-- Toutes --</option>
        <option value="true" <c:if test="${param.disponibilite == 'true'}">selected</c:if>Disponible</option>
        <option value="false" <c:if test="${param.disponibilite == 'false'}">selected</c:if>Indisponible</option>
    </select>


    <button type="submit">Filtrer</button>
</form>

<hr/>
        <c:if test="${param.ajoutSuccess == 'true'}">
            <p style="color: green;">Exemplaire ajouté avec succès.</p>
        </c:if>

<table>
    <thead>
        <tr>
            <th>Livre originel</th>
            <th>Référence exemplaire</th>
            <th>Disponiblité</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="exemplaire" items="${exemplaires}">
            <tr>
                <td>${exemplaire.livre.titre}</td>
                <td>${exemplaire.reference}</td>
                <td>
                    <c:choose>
                        <c:when test="${exemplaire.disponibilite}">Disponible</c:when>
                        <c:otherwise>Indisponible</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="/modifier-exemplaire" method="post" style="display:inline;">
                        <input type="hidden" name="exemplaireId" value="${exemplaire.id}">
                        <input type="submit" value="Modifier">
                    </form>
                    <form action="/supprimer-exemplaire" method="post" style="display:inline;">
                        <input type="hidden" name="exemplaireId" value="${exemplaire.id}">
                        <input type="submit" value="Supprimer">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<c:if test="${not empty sessionScope.bibliothecaire}">
    <br>
    <a href="dashboard">← Retour au dashboard</a>
</c:if>

</body>
</html>
