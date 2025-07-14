<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tableau de bord - Bibliothèque</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #2c3e50;
            color: white;
            padding: 1rem 2rem;
            text-align: center;
        }

        .container {
            max-width: 1000px;
            margin: 2rem auto;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 2rem;
        }

        .card {
            background-color: white;
            padding: 2rem;
            width: 250px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.2s ease-in-out;
            cursor: pointer;
        }

        .card:hover {
            transform: scale(1.05);
        }

        .card h2 {
            font-size: 1.2rem;
            margin-bottom: 1rem;
            color: #2c3e50;
        }

        .card a {
            display: inline-block;
            margin-top: 0.5rem;
            padding: 0.5rem 1rem;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.2s;
        }

        .card a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<header>
    <h1>📚 Tableau de bord - Gestion de la bibliothèque</h1>
</header>

<div class="container">
    <div class="card">
        <h2>Adhérents</h2>
        <a href="liste-adherent">Gérer</a>
    </div>

    <div class="card">
        <h2>Livres</h2>
        <a href="liste-livre">Gérer</a>
    </div>

    <div class="card">
        <h2>Exemplaires</h2>
        <a href="liste-exemplaire">Gérer</a>
    </div>

    <div class="card">
        <h2>Demandes</h2>
        <a href="liste-demande">Gérer</a>
    </div>

    <%-- <div class="card">
        <h2>Emprunts</h2>
        <a href="liste-emprunt">Voir</a>
    </div>

    <div class="card">
        <h2>Réservations</h2>
        <a href="liste-reservation">Voir</a>
    </div>

    <div class="card">
        <h2>Pénalisations</h2>
        <a href="liste-penalisation">Voir</a>
    </div>

    <div class="card">
        <h2>Types d’adhérents</h2>
        <a href="liste-type-adherent">Gérer</a>
    </div>

    <div class="card">
        <h2>Genres de livre</h2>
        <a href="liste-genre">Gérer</a>
    </div>--%>

    <div class="card">
        <h2>Déconnexion</h2>
        <a href="logout">Quitter</a>
    </div> 
</div>

</body>
</html>
