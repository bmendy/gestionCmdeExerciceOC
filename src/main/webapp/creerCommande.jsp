<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div>
            <form method="post" action="creationCommande">
                <fieldset>
                    <legend>Informations client</legend>
    
    				<label for="nvoClient">Nouveau client? <span class="requis">*</span></label>
    				<input type="radio" class="nvoClient" name="nvoClient" value="Oui" onclick="getClientStatus();"/>Oui
    				<input type="radio" class="nvoClient" name="nvoClient" value="Non" onclick="getClientStatus();"/>Non
    				<br/>
    				
    				<div id="clientInfos">
                    <label for="nomClient">Nom <span class="requis">*</span></label>
                    <input type="text" id="nomClient" name="nomClient" value="<c:out value="${commande.client.nom}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreursClient['nomClient']}</span>
                    <br />
                    
                    <label for="prenomClient">Prénom </label>
                    <input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${commande.client.prenom}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreursClient['prenomClient']}</span>
                    <br />
    
                    <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
                    <input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${commande.client.adresseLivraison}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreursClient['adresseClient']}</span>
                    <br />
    
                    <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
                    <input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${commande.client.telephone}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreursClient['telephoneClient']}</span>
                    <br />
                    
                    <label for="emailClient">Adresse email</label>
                    <input type="email" id="emailClient" name="emailClient" value="<c:out value="${commande.client.email}"/>" size="20" maxlength="60" />
                    <span class="erreur">${form.erreursClient['emailClient']}</span>
                    <br />

</div>
<div id="selectClient" class="hidden">
                    <select name="clientExistant">
                     <c:forEach var="client" items="${ sessionScope.listeClients }">
					  <option ><c:out value="${ client.getValue().nom }"/></option>
					  </c:forEach>
					</select>
</div>   
                    
                </fieldset>
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="<c:out value="${commande.date}"/>" size="20" maxlength="20" disabled />
                    <br />
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="<c:out value="${commande.montant}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['montantCommande']}</span>
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="<c:out value="${commande.modePaiement}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['modePaiementCommande']}</span>
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="<c:out value="${commande.statutPaiement}"/>" size="20" maxlength="20" />
                     <span class="erreur">${form.erreurs['statutPaiementCommande']}</span>
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="<c:out value="${commande.modeLivraison}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['modeLivraisonCommande']}</span>
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="<c:out value="${commande.statutLivraison}"/>" size="20" maxlength="20" />
                     <span class="erreur">${form.erreurs['statutLivraisonCommande']}</span>
                    <br />
                    <p class="${empty form.erreurs && empty form.erreursClient ? 'succes' : 'erreur' }">${form.resultat}</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
    <script>
   /*  var nvoClientTableau = document.querySelectorAll('input[name=nvoClient]');
    console.log(nvoClientTableau);
    for (var i=0, length = nvoClientTableau.length; i<length; i++){
    	if(nvoClientTableau[i].checked){
    		console.log(nvoClientTableau[i].value);
    	}
    } */
    
    function getClientStatus(){
    	var nvoClientTableau = document.querySelectorAll('input[name=nvoClient]');
    for (var i=0, length = nvoClientTableau.length; i<length; i++){
    	if(nvoClientTableau[i].checked){
    		console.log(nvoClientTableau[i].value);
    		if(nvoClientTableau[i].value==="Non"){
    			document.getElementById("clientInfos").style.display = "none";
    			document.getElementById("selectClient").style.display = "block";
    		} else {
    			document.getElementById("clientInfos").style.display = "block";
    			document.getElementById("selectClient").style.display = "none";
    		}
    	}
    }
    }   
    </script>
</html>