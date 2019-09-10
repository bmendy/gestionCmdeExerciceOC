package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.forms.CreationCommandeForm;

@WebServlet("/creationCommande")
public class CreationCommande extends HttpServlet{
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		this.getServletContext().getRequestDispatcher( "/creerCommande.jsp" ).forward( request, response );

	}

	Map<String, Commande> listeCommandes;
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		CreationCommandeForm form = new CreationCommandeForm();
		Commande commande = form.creerCommande(request);
				
		HttpSession session = request.getSession();
		
//		String isNouveauClient = request.getParameter("nvoClient");
//		if(isNouveauClient == "oui") {
//			session.setAttribute("isNouveauClient", isNouveauClient);
//		}
		
		if(form.getErreurs().isEmpty() && form.getErreursClient().isEmpty()) {
			if(session.getAttribute("listeCommandes") != null){
				listeCommandes = (Map<String, Commande>) session.getAttribute("listeCommandes");
				listeCommandes.put(commande.getDate(), commande);
			}else {
				listeCommandes = new HashMap<String, Commande>();
				listeCommandes.put(commande.getDate(), commande);
			}
			session.setAttribute("listeCommandes", listeCommandes);
		} else {
			session.setAttribute("listeCommandes", null);
		}
		
		request.setAttribute("commande", commande);
		request.setAttribute("form", form);
		
		if(form.getErreurs().isEmpty() && form.getErreursClient().isEmpty()) {		
		this.getServletContext().getRequestDispatcher( "/afficherCommande.jsp" ).forward( request, response );
	} else {		
		this.getServletContext().getRequestDispatcher("/creerCommande.jsp").forward( request, response );
		}
	}
}
