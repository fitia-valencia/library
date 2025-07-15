<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Réservations</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #aaa;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #eef;
        }
    </style>
</head>
<body>
    <h1>Liste des Réservations</h1>

    <c:if test="${empty reservations}">
        <p>Aucune réservation trouvée.</p>
    </c:if>

    <c:if test="${not empty reservations}">
        <table>
            <thead>
                <tr>
                    <th>Matricule adhérent</th>
                    <th>Date de réservation</th>
                    <th>Référence Exemplaire</th>
                    <th>Exemplaire du livre</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.adherent.matricule}</td>
                        <td>${reservation.dateReservation}</td>
                        <td>${reservation.exemplaire.reference}</td>
                        <td>${reservation.exemplaire.livre.titre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
