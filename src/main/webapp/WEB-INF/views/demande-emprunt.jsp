<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>Mes demandes d’emprunt</h2>

<c:choose>
    <c:when test="${empty demandes}">
        <p>Aucune demande d’emprunt pour le moment.</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
                <tr>
                    <th>Exemplaire</th>
                    <th>Date de demande</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${demandes}">
                    <tr>
                        <td>${d.exemplaire.livre.titre}</td>
                        <td>${d.dateDemande}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>