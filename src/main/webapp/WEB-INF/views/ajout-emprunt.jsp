<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvel emprunt</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }
        form {
            max-width: 400px;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }
        input[type="submit"] {
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h2>Ajouter un nouvel emprunt</h2>

    <form action="${pageContext.request.contextPath}/ajout-emprunt" method="post">

        <label for="dateEmprunt">Date d'emprunt</label>
        <input name="dateEmprunt" id="dateEmprunt" type="date" required="true"/>

        <label for="dateRetourPrevue">Date de retour prévue</label>
        <input name="dateRetourPrevue" id="dateRetourPrevue" type="date" required="true"/>

        <label>Adhérent (matricule) :</label>
    <select name="adherentId" required>
        <option value="">-- Choisir un adhérent --</option>
        <c:forEach var="a" items="${adherents}">
            <option value="${a.id}">${a.matricule}</option>
        </c:forEach>
    </select>

    <label>Exemplaire (référence) :</label>
    <select name="exemplaireId" required>
        <option value="">-- Choisir un exemplaire --</option>
        <c:forEach var="e" items="${exemplaires}">
            <option value="${e.id}">${e.reference}</option>
        </c:forEach>
    </select>
    
    <input type="submit" value="Valider l'emprunt" />
    </form>


</body>
</html>
