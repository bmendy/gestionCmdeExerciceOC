<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des clients</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<c:if test="${!empty sessionScope.listeClients}">
  <p>Voici la liste des clients: 
                 
             
               <%-- Affiche chacune des valeurs pour la clé donnée --%>
               <table>
               <thead>
               <tr>
               <th>Nom</th><th>Prénom</th><th>Adresse</th><th>Téléphone</th><th>Email</th><th>Action</th>
               </tr>
               </thead>
               <tbody>
                <c:forEach var="client" items="${ sessionScope.listeClients }">
               <tr>
               <td><c:out value="${ client.getValue().nom }"/></td>  
               <td><c:out value="${ client.getValue().prenom }"/></td>   
               <td><c:out value="${ client.getValue().adresseLivraison }"/></td>
               <td><c:out value="${ client.getValue().telephone }"/></td>
               <td><c:out value="${ client.getValue().email }"/></td>
               <td>
               <c:set var="supURL">
               <c:url value="/suppressionClient">
               <c:param name="nom" value="${client.getValue().nom}"/>
               </c:url>
               </c:set>
               <a href="${supURL}">Supprimer</a>
               </td>
               </tr>
                </c:forEach>
               </tbody>
               </table>
           
                
  </p>
</c:if>
<c:if test="${empty sessionScope.listeClients}">
<p>Aucun client n'a été crée lors de cette session</p>
</c:if>
</body>
</html>