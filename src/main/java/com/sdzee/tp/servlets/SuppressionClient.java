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
@WebServlet("/suppressionClient")
public class SuppressionClient extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomClient = request.getParameter("nom");
		Map<String, Client> listeClients = (Map<String, Client>) request.getSession().getAttribute("listeClients");
//		for (Map.Entry<String, Client> clt : listeClients.entrySet()) {
//			if (clt.getKey().equals(nomClient)) {
//				listeClients.remove(clt.getKey());
//			}
//		}
		Iterator<String> iterator = listeClients.keySet().iterator();
		while(iterator.hasNext()) {
			String clientDeMap = iterator.next();
			if(clientDeMap.equals(nomClient)) {
				iterator.remove();
			}
		}
		this.getServletContext().getRequestDispatcher("/listeClients").forward(request, response);

	}

}