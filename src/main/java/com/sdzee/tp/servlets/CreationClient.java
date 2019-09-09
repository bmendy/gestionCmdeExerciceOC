package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.forms.CreationClientForm;

@WebServlet(
name = "CreationClient",
description = "This is my first annotated servlet",
urlPatterns = "/creationClient"
)
public class CreationClient extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		this.getServletContext().getRequestDispatcher( "/creerClient.jsp" ).forward( request, response );

	}
	Map<String, Client> listeClients;
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
    
	CreationClientForm form = new CreationClientForm();
	Client client = form.creerClient(request);
	
	
	
	//cela crée la session ou la prend
	HttpSession session =  request.getSession();
	
	//si la session est nouvelle, le tableau des client doit être crée sinon on doit reprendre le même

	if ( form.getErreurs().isEmpty() ) {
		if (session.getAttribute("listeClients") == null) {
			listeClients = new HashMap<String, Client>();
			listeClients.put(client.getNom(),client);	
			session.setAttribute("listeClients", listeClients);
		} else {	
			listeClients = (Map<String, Client>) session.getAttribute("listeClients");
			listeClients.put(client.getNom(),client);
			session.setAttribute("listeClients", listeClients);
		}
	} else {
		session.setAttribute("listeClients", null);
	}
		
	request.setAttribute("client", client);
	request.setAttribute("form", form);
		
    if(form.getErreurs().isEmpty()) {			
	this.getServletContext().getRequestDispatcher( "/afficherClient.jsp" ).forward( request, response );			
	}  else {	 
		this.getServletContext().getRequestDispatcher("/creerClient.jsp").forward( request, response );
	}		
	}
}
