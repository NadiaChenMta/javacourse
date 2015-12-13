package org.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;
import org.mta.javacourse.model.Portfolio;
import org.mta.javacourse.model.Stock;

/**
 * An instance of this class represents PortfolioManager.
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 7/12/15
 */
public class PortfolioManager {

	int i;
	Portfolio myPortfolio = new Portfolio();
	
	/**
	 * create a new portfolio and set all values. 
	 * @return portfolio object
	 */
	public Portfolio getPortfolio() {
		Calendar c = Calendar.getInstance();
		c.set(2015, 12, 4);
		Date date = c.getTime();

		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		Stock stock1 = new Stock("PIH", (float) 10.0, (float) 8.5, date);
		Stock stock2 = new Stock("AAL", (float) 30.0, (float) 25.5 , date);
		Stock stock3 = new Stock("CAAS", (float) 20.0, (float) 15.5, date);
		
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
				
		myPortfolio.buyStock(stock1,20);
		myPortfolio.buyStock(stock2,30);
		myPortfolio.buyStock(stock3,40);
		
		myPortfolio.sellStock("AAL", Portfolio.ALL_STOCKS);
		myPortfolio.removeStock("CAAS");
		
		
		return myPortfolio;
	}

}
