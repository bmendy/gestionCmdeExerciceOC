package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;

public class CreationCommandeForm {
	
	
	private static final String CHAMP_MONTANT = "montantCommande";
	private static final String CHAMP_MODEPAIEMENT = "modePaiementCommande";
	private static final String CHAMP_STATUTPAIEMENT = "statutPaiementCommande";
	private static final String CHAMP_MODELIVRAISON = "modeLivraisonCommande";
	private static final String CHAMP_STATUTLIVRAISON = "statutLivraisonCommande";
	private static final String CHAMP_NVOCLIENT = "nvoClient";
	
	private Map <String,String> erreurs = new HashMap();
	private Map <String,String> erreursClient = new HashMap();
	private String resultat;
	

	
		
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	

	public Map<String, String> getErreursClient() {
		return erreursClient;
	}




	public String getResultat() {
		return resultat;
	}

	private static String getValeurChamp(HttpServletRequest request, String champ){
		String valeur = request.getParameter(champ);
		if(valeur == null || valeur.trim().length()== 0) {
			return null;
		} else {
		return valeur.trim();
	}
	}
	
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}
	
      
    
	public Commande creerCommande(HttpServletRequest request) {
		
		String commandeMontant = getValeurChamp(request, CHAMP_MONTANT);
		String modePaiement = getValeurChamp(request, CHAMP_MODEPAIEMENT);
		String statutPaiement = getValeurChamp(request, CHAMP_STATUTPAIEMENT);
		String modeLivraison = getValeurChamp(request, CHAMP_MODELIVRAISON);
		String statutLivraison = getValeurChamp(request, CHAMP_STATUTLIVRAISON);
		
		
		Commande commande = new Commande();
		double valeurMontant = -1;
		 try {
			 valeurMontant = validationMontant(commandeMontant);
		 } catch (Exception e){
			 setErreur(CHAMP_MONTANT, e.getMessage());
		 }
		 commande.setMontant(valeurMontant);
		 
		 try {
			 validationModePaiement(modePaiement);
		 } catch(Exception e) {
			 setErreur(CHAMP_MODEPAIEMENT, e.getMessage());
		 }
		 commande.setModePaiement(modePaiement);
		 
		 try {
			 validationStatutPaiement(statutPaiement);
		 } catch (Exception e) {
			 setErreur(CHAMP_STATUTPAIEMENT, e.getMessage());
		 }
		 commande.setStatutPaiement(statutPaiement);
		 
		 try {
			 validationModeLivraison(modeLivraison);
		 } catch (Exception e) {
			 setErreur(CHAMP_MODELIVRAISON, e.getMessage());
		 }
		 commande.setModeLivraison(modeLivraison);
		 
		 try {
			 validationStatutLivraison(statutLivraison);
		 } catch (Exception e) {
			 setErreur(CHAMP_STATUTLIVRAISON, e.getMessage());
		 }
		 commande.setStatutLivraison(statutLivraison);
		 
		 DateTime dt = new DateTime();
		 String date = dt.toString("dd/MM/yyyy HH:mm:ss");
		 commande.setDate(date);
		 
		 Client client = null;
		 String nvoClient = getValeurChamp(request, CHAMP_NVOCLIENT);
			String nomClientExistant = getValeurChamp(request, "clientExistant");
			if(nvoClient.equals("Oui")) {		
				CreationClientForm creationClientForm = new CreationClientForm();
			    client = creationClientForm.creerClient(request);		    
			    erreursClient = creationClientForm.getErreurs();
				} else {
				 	Map<String, Client> listeClients = (Map<String, Client>) request.getSession().getAttribute("listeClients");
				 	for (Map.Entry<String, Client> clt: listeClients.entrySet()) {
				 		if (clt.getKey().equals(nomClientExistant)) {
				 			client = new Client();
				 			client.setNom(clt.getKey());
				 			client.setPrenom(clt.getValue().getPrenom());
				 			client.setAdresseLivraison(clt.getValue().getAdresseLivraison());
				 			client.setTelephone(clt.getValue().getTelephone());
				 			client.setEmail(clt.getValue().getEmail());
				 		}
				 	}
				}
		 
		 commande.setClient(client);
		 
		 
		 if(erreurs.isEmpty() && erreursClient.isEmpty()) {
	    		resultat = "Succès de la création";
	    	} else {
	    		resultat = "Echec de la création";
	    	}
		 
		 return commande;
		
	}
	
//	private Client dertermineClientCommande(HttpServletRequest request) {
//		
//		return client;
//	}
	
	private void validationModePaiement(String pModePaiement)throws Exception {
		if(pModePaiement!= null ) {
			if(pModePaiement.trim().length() < 2) {
				throw new Exception("Le mode de paiement doit contenir au moins 2 caractères");
			}
		} else {
			throw new Exception("Veuillez rensigner le mode de paiement");
		}
	}
	
	private void validationStatutPaiement(String pStatutPaiement) throws Exception{
		if(pStatutPaiement!= null && pStatutPaiement.trim().length() < 2) {
			throw new Exception("Le statut du paiement doit contenir au moins 2 caractères");	
		} 
	}
	
	private void validationModeLivraison(String pModeLivraison)throws Exception {
		if(pModeLivraison!= null ) {
			if(pModeLivraison.trim().length() < 2) {
				throw new Exception("Le mode de livraison doit contenir au moins 2 caractères");
			}
		} else {
			throw new Exception("Veuillez rensigner le mode de livraison");
		}
	}
	
	private void validationStatutLivraison(String pStatutLivraison) throws Exception{
		if(pStatutLivraison!= null && pStatutLivraison.trim().length() < 2) {
			throw new Exception("Le statut de livraison doit contenir au moins 2 caractères");	
		}
	}
	
	private double validationMontant(String pmontant) throws Exception{
		double d;
		
		if(pmontant != null) { 
			try {
				d = Double.parseDouble(pmontant);
				if(d < 0) {
					throw new Exception("Le montant doit être positif");
				}
			} catch (NumberFormatException e) {
				    d = -1;
					throw new Exception("Le montant doit être un nombre");					
			}			
		} else {
			d = -1;
			throw new Exception("Veuillez renseigner le montant");
		}
		return d;
	}
	
}
