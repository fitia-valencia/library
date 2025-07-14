<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des adhérents</title>
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
    <h1>Liste des adhérents</h1>

    <a href="ajout-adherent">Ajouter un adhérent</a>
    <c:if test="${not empty message}">
        <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
            ${message}
        </div>
    </c:if>

    <form method="get" action="liste-adherent">
        <label for="typeId">Type d’adhérent :</label>
        <select name="typeId" id="typeId">
            <option value="">Tous</option>
            <c:forEach var="type" items="${typeAdherent}">
                <option value="${type.id}" ${param.typeId == type.id ? 'selected' : ''}>${type.libelle}</option>
            </c:forEach>
        </select>

        <label for="statut">Statut :</label>
        <select name="statut" id="statut">
            <option value="">Tous</option>
            <option value="actif" ${param.statut == 'actif' ? 'selected' : ''}>Actif</option>
            <option value="inactif" ${param.statut == 'inactif' ? 'selected' : ''}>Inactif</option>
        </select>

        <button type="submit">Filtrer</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Type adhérent</th>
                <th>Statut</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="adherent" items="${adherents}">
                <tr>
                    <td>${adherent.matricule}</td>
                    <td>${adherent.nom}</td>
                    <td>${adherent.prenom}</td>
                    <td>${adherent.typeAdherent.libelle}</td>
                    <td>
                        <c:choose>
                            <c:when test="${adherent.isActive()}">
                                <span style="color: green;">Actif</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red;">Inactif</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="details-adherent?adherentId=${adherent.id}">Détails</a>
                    </td>

                </tr>
        </c:forEach>
    </tbody>
</table>
<br>
<a href="dashboard">← Retour au dashboard</a>

</body>
</html>
