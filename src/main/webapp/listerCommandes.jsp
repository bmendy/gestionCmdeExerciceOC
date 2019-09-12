<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des commandes</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>

<c:if test="${!empty sessionScope.listeCommandes}">
  <p>Voici la liste des commandes: 
                 
             
               <%-- Affiche chacune des valeurs pour la clé donnée --%>
               <table>
               <thead>
               <tr>
               <th>Client</th><th>Date</th><th>Montant</th><th>Mode de paiement</th><th>Statut de paiment</th>
               <th>Mode de livraison</th><th>Statut de livraison</th><th>Action</th>
               </tr>
               </thead>
               <tbody>
                <c:forEach var="commande" items="${ sessionScope.listeCommandes }">
               <tr>
               <td><c:out value="${ commande.getValue().client.nom }"/> <c:out value="${ commande.getValue().client.prenom }"/></td>  
               <td><c:out value="${ commande.getValue().date }"/></td>   
               <td><c:out value="${ commande.getValue().montant }"/></td>
               <td><c:out value="${ commande.getValue().modePaiement }"/></td>
               <td><c:out value="${ commande.getValue().statutPaiement }"/></td>
               <td><c:out value="${ commande.getValue().modeLivraison }"/></td>
               <td><c:out value="${ commande.getValue().statutLivraison }"/></td>
               <td>Supprimer</td>
               </tr>
                </c:forEach>
               </tbody>
               </table>
           
                
  </p>
</c:if>
<c:if test="${empty sessionScope.listeCommandes}">
<p>Aucune commande n'a été crée lors de cette session</p>
</c:if>
</body>
</html>