<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détails de l'adhérent</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 2rem;
            background-color: #f5f5f5;
        }
        h1, h2 {
            color: #2c3e50;
        }
        .detail {
            background-color: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px #ccc;
            max-width: 600px;
        }
        .field {
            margin-bottom: 1rem;
        }
        .label {
            font-weight: bold;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            background-color: white;
            box-shadow: 0 0 8px #ccc;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 0.75rem;
            text-align: left;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        .no-data {
            font-style: italic;
            color: #888;
        }
        a {
            text-decoration: none;
            color: #3498db;
        }
    </style>
</head>
<body>

<h1>Détails de l'adhérent</h1>

<div class="detail">
    <div class="field"><span class="label">Statut :</span> 
        <c:choose>
            <c:when test="${adherent.isActive()}">
                <span style="color: green;">Actif</span>
            </c:when>
            <c:otherwise>
                <span style="color: red;">Inactif</span>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="field"><span class="label">Matricule :</span> ${adherent.matricule}</div>
    <div class="field"><span class="label">Nom :</span> ${adherent.nom}</div>
    <div class="field"><span class="label">Prénom :</span> ${adherent.prenom}</div>
    <div class="field"><span class="label">Date de naissance :</span> ${adherent.dateNaissance}</div>
    <div class="field"><span class="label">Téléphone :</span> ${adherent.telephone}</div>
    <div class="field"><span class="label">Date d'inscription :</span> ${adherent.dateInscription}</div>
    <div class="field"><span class="label">Type adhérent :</span> ${adherent.typeAdherent.libelle}</div>
    <div class="field"><span class="label">Durée abonnement :</span> ${adherent.typeAdherent.dureeAbonnement} mois</div>
</div>

<br>
<h2>Ses emprunts</h2>
<c:choose>
    <c:when test="${not empty emprunts}">
        <table>
            <tr><th>Livre</th><th>Référence</th><th>Date d’emprunt</th><th>Retour prévu</th><th>Retour réel</th></tr>
            <c:forEach var="e" items="${emprunts}">
                <tr>
                    <td>${e.exemplaire.livre.titre}</td>
                    <td>${e.exemplaire.reference}</td>
                    <td>${e.dateEmprunt}</td>
                    <td>${e.dateRetourPrevue}</td>
                    <td><c:out value="${e.dateRetourReelle}" default="Pas encore rendu"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="no-data">Cet adhérent n’a encore effectué aucun emprunt.</p>
    </c:otherwise>
</c:choose>

<br>
<h2>Ses réservations</h2>
<c:choose>
    <c:when test="${not empty reservations}">
        <table>
            <tr><th>Livre</th><th>Référence</th><th>Date</th></tr>
            <c:forEach var="r" items="${reservations}">
                <tr>
                    <td>${r.exemplaire.livre.titre}</td>
                    <td>${r.exemplaire.reference}</td>
                    <td>${r.dateReservation}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="no-data">Cet adhérent n’a encore effectué aucune réservation.</p>
    </c:otherwise>
</c:choose>
<br>
<h2>Ses pénalités</h2>
<c:choose>
    <c:when test="${not empty penalites}">
        <table>
            <tr>
                <th>Motif</th>
                <th>Date de début</th>
                <th>Date de fin</th>
            </tr>
            <c:forEach var="p" items="${penalites}">
                <tr>
                    <td>${p.motif}</td>
                    <td>${p.dateDebut}</td>
                    <td>${p.dateFin}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p class="no-data">Cet adhérent n’a aucune pénalisation.</p>
    </c:otherwise>
</c:choose>

<br>
<a href="liste-adherent">← Retour à la liste</a>

</body>
</html>
