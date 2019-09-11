package com.sdzee.tp.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Client;

public class CreationClientForm {
	
	private static final String CHAMP_NOM = "nomClient";
	private static final String CHAMP_PRENOM = "prenomClient";
	private static final String CHAMP_ADRESSE = "adresseClient";
	private static final String CHAMP_TEL = "telephoneClient";
	private static final String CHAMP_EMAIL = "emailClient";
	
	private String resultat;
	private Map<String,String> erreurs = new HashMap<String,String>();
	
	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
	    erreurs.put( champ, message );
	}
	
	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}   
	
	public Client creerClient(HttpServletRequest request) {
		
		String emailParam = getValeurChamp(request, CHAMP_EMAIL);
		String nomParam = getValeurChamp(request, CHAMP_NOM);
		String prenomParam = getValeurChamp(request, CHAMP_PRENOM);
		String adresseParam = getValeurChamp(request, CHAMP_ADRESSE);
		String telParam = getValeurChamp(request, CHAMP_TEL);
		
		Client client = new Client();
		
		try {
			validationNom(nomParam);
		} catch (Exception e){
			setErreur(CHAMP_NOM, e.getMessage());
		}
		client.setNom(nomParam);
		
		try {
			validationPrenom(prenomParam);
		} catch (Exception e){
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		client.setPrenom(prenomParam);
		
		try {
			validationAdresse(adresseParam);
		} catch (Exception e){
			setErreur(CHAMP_ADRESSE, e.getMessage());
		}
		client.setAdresseLivraison(adresseParam);
		
		try {
			validationTel(telParam);
		} catch (Exception e){
			setErreur(CHAMP_TEL, e.getMessage());
		}
		client.setTelephone(telParam);
		
		try {
			validationEmail(emailParam);
		} catch (Exception e){
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		client.setEmail(emailParam);
		
		if(erreurs.isEmpty()) {
    		resultat = "Succès de la création";
    	} else {
    		resultat = "Echec de la création";
    	}
		
		return client;		
	}
	
	private void validationNom(String pnomUtilisParam) throws Exception{
    	if(pnomUtilisParam != null) {
    		if(pnomUtilisParam.trim().length() <2){
    		throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères");
    		}
    	}else {
    		throw new Exception("Veuillez remplir le nom");
    	}
    }
	
	private void validationAdresse(String padresse) throws Exception{
    	if (padresse != null) {
    		if(padresse.trim().length() < 10) {
    			throw new Exception("L'adresse d'utilisateur doit contenir au moins 10 caractères");
    		}
    	}else {
    		throw new Exception("Veuillez remplir l'adresse de livraison");
    	}
    }
	
	private void validationPrenom(String pnom) throws Exception{
    	if (pnom != null && pnom.trim().length() <2) {
    			throw new Exception("Le prenom d'utilisateur doit contenir au moins 2 caractères");
    		}
    }
	
	private void validationTel(String ptel) throws Exception{
		if (ptel != null) {
		String[] tabCarac = ptel.split("(?!^)");
		for(String el : tabCarac) {
		try {
			Integer.parseInt(el);
		} catch (NumberFormatException e) {
			throw new Exception("Veuillez entrer des numéros");
		};
		}
    	if (ptel.trim().length() <4) {
    			throw new Exception("Le numéro de tel de l'utilisateur doit contenir au moins 4 numeros");
    		}
		}else {
			throw new Exception("Veuillez rensigner le numéro de tel");
		}
    }

	private void validationEmail(String pemail) throws Exception{
    	if (pemail != null) {
    		if( !pemail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
    			throw new Exception("Merci de saisir une adresse mail valide");
    		}
    	}
    }
	 

}
