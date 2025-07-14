<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Emprunts</title>
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
        .menu {
    background-color: #2c3e50;
    overflow: hidden;
    padding: 10px 0;
    margin-bottom: 2rem;
}

.menu {
    background-color: #2c3e50;
    overflow: hidden;
    padding: 10px 0;
    margin-bottom: 2rem;
    display: flex;
    align-items: center;
    gap: 1rem;
}

.menu a {
    color: white;
    text-align: center;
    padding: 12px 16px;
    text-decoration: none;
    font-weight: bold;
}

.menu a:hover {
    background-color: #1abc9c;
}

.menu-select {
    padding: 8px;
    border: none;
    font-weight: bold;
    background-color: #3498db;
    color: white;
    border-radius: 4px;
    cursor: pointer;
}

.menu-select:hover {
    background-color: #1abc9c;
}


    </style>
</head>
<body>

<h1>Liste des Emprunts</h1>
    <c:if test="${not empty sessionScope.bibliothecaire}">
        <a href="${pageContext.request.contextPath}/ajout-emprunt">+ Ajouter un emprunt</a>
        <c:if test="${param.ajoutSuccess == 'true'}">
            <p style="color: green;">Emprunt ajouté avec succès.</p>
        </c:if>
    </c:if>
    <%-- <c:if test="${not empty genres}">
        <form method="get" action="${pageContext.request.contextPath}/liste-emprunt">
            <label for="genreId">Genre :</label>
            <select name="genreId" id="genreId">
                <option value="">-- Tous --</option>
                <c:forEach var="genre" items="${genres}">
                    <option value="${genre.id}" <c:if test="${param.genreId == genre.id}">selected</c:if>>${genre.libelle}</option>
                </c:forEach>
            </select>

            <label for="ageMin">Pour adhérent de:</label>
            <input type="number" name="ageMin" id="ageMin" min="0" placeholder="age" value="${param.ageMin}"/> ans

            <input type="submit" value="Filtrer"/>
        </form>
    </c:if> --%>

<table>
    <thead>
        <tr>
            <th>Matricule Adherent</th>
            <th>Référence Exemplaire</th>
            <th>Livre associé</th>
            <th>Date d'emprunt</th>
            <th>Date de retour prévue</th>
            <th>Date de retour réelle</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="emprunt" items="${emprunts}">
            <tr>
                <td>${emprunt.adherent.matricule}</td>
                <td>${emprunt.exemplaire.reference}</td>
                <td>${emprunt.exemplaire.livre.titre}</td>
                <td><c:out value="${emprunt.dateEmprunt}" /></td>
                <td><c:out value="${emprunt.dateRetourPrevue}" /></td>
                <td>
                <c:choose>
                    <c:when test="${empty emprunt.dateRetourReelle}">
                    Pas encore retourné
                    </c:when>
                    <c:otherwise>
                    <c:out value="${emprunt.dateRetourReelle}" />
                    </c:otherwise>
                </c:choose>
                </td>                
                <td>
                    <form action="/retourner" method="post" style="display:inline;">
                        <input type="hidden" name="empruntId" value="${emprunt.id}">
                        <input type="submit" value="Retourner">
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
