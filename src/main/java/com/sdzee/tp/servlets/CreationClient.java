package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
    CreationClientForm form = new CreationClientForm();
	Client client = form.creerClient(request);
		
	request.setAttribute("client", client);
	request.setAttribute("form", form);
		
    if(form.getErreurs().isEmpty()) {			
	this.getServletContext().getRequestDispatcher( "/afficherClient.jsp" ).forward( request, response );			
	}  else {	 
		this.getServletContext().getRequestDispatcher("/creerClient.jsp").forward( request, response );
	}		
	}
}
