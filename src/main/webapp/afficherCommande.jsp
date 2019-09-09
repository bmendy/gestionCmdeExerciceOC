<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Afficher récapitulatif commande</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<p class="info">
<p class="${empty form.erreurs && empty form.erreursClient ? 'succes' : 'erreur' }">${form.resultat}</p>
</p>
<p>
Client: </br>
Nom: ${commande.client.nom} </br>
Prénom: ${commande.client.prenom} </br>
Adresse: ${commande.client.adresseLivraison}</br>
Numéro de téléphone: ${commande.client.telephone}</br>
Email: ${commande.client.email}
</p>
<p>
Commande: </br>
Montant de la commande: ${commande.montant}</br>
Mode de paiment: ${commande.modePaiement} </br>
Statut du paiment: ${commande.statutPaiement} </br>
Mode de livraison: ${commande.modeLivraison}</br>
Satut de la livraison: ${commande.statutLivraison}</br>
Date: ${commande.date} 
</p>
</body>
</html>