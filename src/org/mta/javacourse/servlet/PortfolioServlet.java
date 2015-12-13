package org.mta.javacourse.servlet;

import java.io.IOException;
import javax.servlet.http.*;
import org.mta.javacourse.model.Portfolio;
import org.mta.javacourse.service.PortfolioManager;

/**
 * An instance of this class represents a portfolio servlet 
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 7/12/15
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PortfolioManager portfolioManager = new PortfolioManager();

		Portfolio myPortfolio = portfolioManager.getPortfolio();

		resp.getWriter().println(myPortfolio.getHtmlPortfolio());

	
	}
}
