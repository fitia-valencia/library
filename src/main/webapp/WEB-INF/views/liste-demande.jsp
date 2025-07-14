<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Demandes</title>
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
<h1>Liste des Demandes</h1>
        <h2>Filtrer les demandes</h2>

<%-- <form action="liste-demande" method="get">
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
</form> --%>

<hr/>

<table>
    <thead>
        <tr>
            <th>Matricule de l'adhérent</th>
            <th>Type de demande</th>
            <th>Exemplaire demandé</th>
            <th>Pour le livre</th>
            <th>Date de demandée</th>
            <th>Date envoie de demande</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="demande" items="${demandes}">
            <tr>
                <td>${demande.adherent.matricule}</td>
                <td>${demande.typeDemande.libelle}</td>
                <td>${demande.exemplaire.reference}</td>
                <td>${demande.exemplaire.livre.titre}</td>
                <td>${demande.dateDemande}</td>
                <td>${demande.dateEnvoiDemande}</td>
                <td>
                    <form action="/valider-demande" method="post" style="display:inline;">
                        <input type="hidden" name="demandeId" value="${demande.id}">
                        <input type="submit" value="Valider">
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
