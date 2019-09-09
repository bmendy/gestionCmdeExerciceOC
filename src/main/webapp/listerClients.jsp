<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty sessionScope.listeClients}">
  <p class="succes">Voici la liste des clients: 
                 
             
               <%-- Affiche chacune des valeurs pour la clé donnée --%>
               <table>
               <thead>
               <tr>
               <th>Nom</th><th>Prénom</th><th>Adresse</th><th>Téléphone</th><th>Email</th>
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
               </tr>
                </c:forEach>
               </tbody>
               </table>
           
                
  </p>
</c:if>
</body>
</html>