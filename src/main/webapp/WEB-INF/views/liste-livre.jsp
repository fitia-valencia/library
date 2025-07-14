<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
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
<c:if test="${not empty sessionScope.adherent}">
        <div class="menu">
        <a href="${pageContext.request.contextPath}/liste-livre">Livres</a>
        <select onchange="if(this.value) window.location.href=this.value" class="menu-select">
            <option value="">-- Demandes --</option>
            <option value="${pageContext.request.contextPath}/demande-emprunt">Emprunts</option>
            <option value="${pageContext.request.contextPath}/demande-reservation">Réservations</option>
            <option value="${pageContext.request.contextPath}/demande-prolongement">Prolongements</option>
        </select>
        <a href="${pageContext.request.contextPath}/liste-penalisation">Pénalisations</a>
        <a href="${pageContext.request.contextPath}/emprunt-actuel">Emprunts actuels</a>
        <a href="${pageContext.request.contextPath}/logout" style="float: right;">Se déconnecter</a>
    </div>

</c:if>

<h1>Liste des Livres</h1>
    <c:if test="${not empty sessionScope.bibliothecaire}">
        <a href="${pageContext.request.contextPath}/ajout-livre">+ Ajouter un livre</a>
        <c:if test="${param.ajoutSuccess == 'true'}">
            <p style="color: green;">Livre ajouté avec succès.</p>
        </c:if>
    </c:if>
    <c:if test="${not empty genres}">
        <form method="get" action="${pageContext.request.contextPath}/liste-livre">
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
    </c:if>

<table>
    <thead>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Genre</th>
            <th>Date de publication</th>
            <th>Éditeur</th>
            <th>Âge minimum</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.titre}</td>
                <td>${livre.auteur}</td>
                <td>${livre.genre.libelle}</td>
                <td><c:out value="${livre.datePublication}" /></td>
                <td>${livre.editeur}</td>
                <td>${livre.restrictionAge} ans</td>
                <c:if test="${not empty sessionScope.adherent}">
                    <td>
                        <form action="/emprunter" method="post" style="display:inline;">
                            <input type="hidden" name="livreId" value="${livre.id}">
                            <input type="hidden" name="typeDemandeId" value="1">
                            <input type="submit" value="Emprunter">
                        </form>

                        <form action="/reserver" method="post" style="display:inline;">
                            <input type="hidden" name="livreId" value="${livre.id}">
                            <input type="submit" value="Réserver">
                        </form>
                    </td>
                </c:if>
                <c:if test="${not empty sessionScope.bibliothecaire}">
                    <td>
                        <form action="/modifier-livre" method="post" style="display:inline;">
                            <input type="hidden" name="livreId" value="${livre.id}">
                            <input type="submit" value="Modifier">
                        </form>

                        <form action="/supprimer-livre" method="post" style="display:inline;">
                            <input type="hidden" name="livreId" value="${livre.id}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                </c:if>
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
