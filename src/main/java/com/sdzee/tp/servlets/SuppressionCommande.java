package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Client;

@WebServlet("/suppressionCommande")
public class SuppressionCommande extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateCommande = request.getParameter("date");
		Map<String, Client> listeCommandes = (Map<String, Client>) request.getSession().getAttribute("listeCommandes");
		try {
		Iterator<String> iterator = listeCommandes.keySet().iterator();
		while(iterator.hasNext()) {
			String commandeDeMap = iterator.next();
			if(commandeDeMap.equals(dateCommande)) {
				iterator.remove();
			}
		}
		this.getServletContext().getRequestDispatcher("/listeCommandes").forward(request, response);
		} finally {
			
		}
	}
}
