<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ajout d’un exemplaire</title>
</head>
<body>
    <h2>Ajouter un exemplaire</h2>
    
    <form action="${pageContext.request.contextPath}/ajout-exemplaire" method="post">
        <label>Livre :</label>
        <select name="livreId" required>
            <c:forEach items="${livres}" var="livre">
                <option value="${livre.id}">${livre.titre} (${livre.isbn})</option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" value="Ajouter l’exemplaire" />
    </form>
</body>
</html>
