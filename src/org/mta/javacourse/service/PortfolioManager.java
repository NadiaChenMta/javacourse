package org.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;
import org.mta.javacourse.model.Portfolio;
import org.mta.javacourse.model.Stock;

public class PortfolioManager {

	int i;
	Portfolio portfolio1 = new Portfolio();

	/**
	 * 
	 * @return
	 */
	public Portfolio getPortfolio() {
		Calendar c = Calendar.getInstance();
		c.set(2014, 11, 15);
		Date date = c.getTime();

		Stock stock1 = new Stock("PIH", (float) 13.1, (float) 12.4, date);
		Stock stock2 = new Stock("AAL", (float) 5.78, (float) 5.5, date);
		Stock stock3 = new Stock("CAAS", (float) 32.2, (float) 31.5, date);

		portfolio1.addStock(stock1);
		portfolio1.addStock(stock2);
		portfolio1.addStock(stock3);

		portfolio1.setTitle("Portfolio #1");

		portfolio1.getStocks();

		return portfolio1;
	}

}
