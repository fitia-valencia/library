<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Réserver un livre</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 2em;
            background-color: #f9f9f9;
        }

        .form-container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 2em;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 1em;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 0.5em;
            margin-top: 0.5em;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .btn {
            margin-top: 2em;
            padding: 0.7em 1.5em;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            display: block;
            width: 100%;
            font-size: 1em;
        }

        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Demande de réservation</h2>

    <form method="post" action="reserver">
        <!-- ID du livre caché -->
        <input type="hidden" name="idLivre" value="${livre.id}" />

        <!-- Affichage des infos du livre -->
        <p><strong>Titre :</strong> ${livre.titre}</p>
        <p><strong>Auteur :</strong> ${livre.auteur}</p>
        <p><strong>Éditeur :</strong> ${livre.editeur}</p>
        <p><strong>Genre :</strong> ${livre.genre.libelle}</p>
        <p><strong>Âge minimum requis :</strong> ${livre.restrictionAge} ans</p>

        <!-- Date de demande -->
        <label for="dateDemande">Date de demande</label>
        <input type="date" id="dateDemande" name="dateDemande" required />

        <button type="submit" class="btn">Envoyer la demande</button>
    </form>
</div>

</body>
</html>
