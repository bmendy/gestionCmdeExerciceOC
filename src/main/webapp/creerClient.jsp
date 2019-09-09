<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <link type="text/css" rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div>
            <form method="post" action="creationClient">
                <fieldset>
                    <legend>Informations client</legend>
    
                    <label for="nomClient">Nom <span class="requis">*</span></label>
                    <input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nom}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['nomClient']}</span>
                    <br />
                    
                    <label for="prenomClient">Prénom </label>
                    <input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenom}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['prenomClient']}</span>
                    <br />
    
                    <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
                    <input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${client.adresseLivraison}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['adresseClient']}</span>
                    <br />
    
                    <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
                    <input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${client.telephone}"/>" size="20" maxlength="20" />
                    <span class="erreur">${form.erreurs['telephoneClient']}</span>
                    <br />
                    
                    <label for="emailClient">Adresse email</label>
                    <input type="email" id="emailClient" name="emailClient" value="<c:out value="${client.email}"/>" size="20" maxlength="60" />
                    <span class="erreur">${form.erreurs['emailClient']}</span>
                    <br />
                    <p class="${empty form.erreurs ? 'succes' : 'erreur' }">${form.resultat}</p>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>