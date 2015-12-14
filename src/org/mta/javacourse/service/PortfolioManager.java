package org.mta.javacourse.service;

import org.mta.javacourse.model.Portfolio;
import org.mta.javacourse.model.Stock;

/**
 * An instance of this class represents PortfolioManager.
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 14/12/15
 */
public class PortfolioManager {
	/**
	 * Inner-class of PortfolioManager:
	 * 1. Create new portfolio and set values.
	 * 2. Create stock objects.
	 * 3. Trade stocks(buying and selling).
	 * 
	 * @return portfolio object
	 */
	public Portfolio getPortfolio() {
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);

		// Initiate Stocks
		Stock stock1 = new Stock("PIH", (float) 10.0, (float) 8.5, "12/15/2014");
		Stock stock2 = new Stock("AAL", (float) 30.0, (float) 25.5, "12/15/2014");
		Stock stock3 = new Stock("CAAS", (float) 20.0, (float) 15.5, "12/15/2014");		
			
		// Trade Stocks
		myPortfolio.buyStock(stock1, 20);
		myPortfolio.buyStock(stock2, 30);
		myPortfolio.buyStock(stock3, 40);
				
		myPortfolio.sellStock("AAL", Portfolio.ALL_STOCKS);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
