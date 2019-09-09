<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Afficher récapitulatif client</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<p>
<p class="info">
 <p class="${empty form.erreurs ? 'succes' : 'erreur' }">${form.resultat}</p>
</p>
</p>
<p>
Nom: ${client.nom} </br>
Prénom: ${client.prenom} </br>
Adresse: ${client.adresseLivraison}</br>
Numéro de téléphone: ${client.telephone}</br>
Email: ${client.email}
</p>
</body>
</html>