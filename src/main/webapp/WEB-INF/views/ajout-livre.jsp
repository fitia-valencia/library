<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajout d’un Livre</title>
</head>
<body>
    <h2>Ajout d’un nouveau livre</h2>

    <form action="ajout-livre" method="post">
        <label>ISBN :</label>
        <input type="text" name="isbn" required /><br/>

        <label>Titre :</label>
        <input type="text" name="titre" required /><br/>

        <label>Auteur :</label>
        <input type="text" name="auteur" required /><br/>

        <label>Genre :</label>
        <select name="genre.id" required>
            <c:forEach var="g" items="${genres}">
                <option value="${g.id}">${g.libelle}</option>
            </c:forEach>
        </select><br/>

        <label>Date de publication :</label>
        <input type="date" name="datePublication" required /><br/>

        <label>Éditeur :</label>
        <input type="text" name="editeur" /><br/>

        <label>Restriction d’âge :</label>
        <input type="number" name="restrictionAge" required min="0"/><br/>

        <input type="submit" value="Ajouter" />
    </form>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <a href="${pageContext.request.contextPath}/liste-livre">← Retour à la liste</a>
</body>
</html>
