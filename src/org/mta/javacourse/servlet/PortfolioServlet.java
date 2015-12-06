package org.mta.javacourse.servlet;

import java.io.IOException;
import javax.servlet.http.*;
import org.mta.javacourse.model.Portfolio;
import org.mta.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		PortfolioManager portfolioManager = new PortfolioManager();

		Portfolio portfolio1 = portfolioManager.getPortfolio();

		Portfolio portfolio2 = new Portfolio(portfolio1);

		portfolio2.setTitle("portfolio #2");

		resp.getWriter().println(portfolio1.getHtmlPortfolio());
		resp.getWriter().println(portfolio2.getHtmlPortfolio());

		resp.getWriter().println("---------------------------------------------------------------------------------------");
		portfolio1.removeStock(1);

		resp.getWriter().println(portfolio1.getHtmlPortfolio());
		resp.getWriter().println(portfolio2.getHtmlPortfolio());

		resp.getWriter().println("---------------------------------------------------------------------------------------");
		portfolio2.changeStockBid(3, 55.55f);

		resp.getWriter().println(portfolio1.getHtmlPortfolio());
		resp.getWriter().println(portfolio2.getHtmlPortfolio());

	}
}
