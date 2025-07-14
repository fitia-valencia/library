<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes pénalisations</title>
    <style>
        body { font-family: Arial; margin: 2rem; background-color: #f7f7f7; }
        h1 { text-align: center; color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 2rem; background: white; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #3498db; color: white; }
        tr:hover { background-color: #f0f8ff; }
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
            <option value="${pageContext.request.contextPath}/demandes-emprunts">Emprunts</option>
            <option value="${pageContext.request.contextPath}/demandes-reservation">Réservations</option>
            <option value="${pageContext.request.contextPath}/demandes-prolongement">Prolongements</option>
        </select>
        <a href="${pageContext.request.contextPath}/liste-penalisation">Pénalisations</a>
        <a href="${pageContext.request.contextPath}/emprunt-actuel">Emprunts actuels</a>
        <a href="${pageContext.request.contextPath}/logout" style="float: right;">Se déconnecter</a>
    </div>
    <h1>Mes pénalisations</h1>

    <c:if test="${empty penalites}">
        <p>Aucune pénalisation en cours.</p>
    </c:if>

    <c:if test="${not empty penalites}">
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Durée (jours)</th>
                    <th>Motif</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${penalites}">
                    <tr>
                        <td>${p.datePenalisation}</td>
                        <td>${p.dureePenalisation}</td>
                        <td>${p.motif}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
