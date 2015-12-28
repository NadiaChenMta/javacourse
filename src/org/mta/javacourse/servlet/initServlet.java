package org.mta.javacourse.servlet;

import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import org.mta.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class initServlet extends javax.servlet.http.HttpServlet
 {
	@Override
	public void init() throws ServletException {
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	}
}