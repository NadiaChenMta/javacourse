package org.mta.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

/**
 * An instance of this class represents portfolio that contains stocks.
 * 
 * #Variables: 
 * title(string), i(int), portfolioSize(int), stocks[](stock array).
 * #C'tors: 
 * 1. public Portfolio() 
 * 2. public Portfolio(Portfolio portfolio) - copy of portfolio
 * 
 * #Methods: 
 * 1. getters and setters
 * 2. public void  addStock(Stock stock)
 * 3. public boolean buyStock(Stock stock, int quantity)
 * 4. public void removeStock(int stockNum)
 * 5. public boolean sellStock(String symbol, int quantity)
 * 6. public void changeStockBid(int stockNum, float bid) 
 * 7. public String getHtmlPortfolio()
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 14/12/15
 */
public class Portfolio implements PortfolioInterface{
	public static final int MAX_PORTFOLIO_SIZE = 5;
	public static final int ALL_STOCKS = -1;
	private String title;
	private float balance;
	private StockInterface[] stocks;
	private int portfolioSize;
	

	/**
	 * constructor
	 */
	public Portfolio() {
		stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.title = "unknown";
		portfolioSize = 0;
		balance = 0;
	}

	public Portfolio(Stock[] stockArray) {
		this.title = new String();
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = stockArray.length;
		for (int i = 0; i < portfolioSize; i++) {
		this.stocks[i] = stockArray[i];
		}
	}
	
	/**
	 * copy constructor
	 * 
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.portfolioSize;
		this.balance = getBalance();
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			stocks[i] = new Stock((portfolio.stocks[i]));
		}
	}
	
	// getters & setters
	public String getTitle() {
		return title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public float getBalance() {
		return balance;
	}
	
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
		
	public StockInterface[] getStocks() {
		return stocks;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}

	
	// methods
	/**
	 * change the current balance.
	 * portfolio owner can deposit or withdraw.
	 * selling stocks- balance increase.
	 * buying stocks- balance decrease.
	 * 
	 * @param amount
	 */
	public void updateBalance(float amount) {
		if (this.balance + amount < 0) {
			System.out.println("balance cannot be negative!");
		} else {
			balance += amount;
			System.out.println("Balance updated with " + amount + "$");
		}
	}

	/**
	 * add stock to portfolio if it doesn't exist and the portfolio is not full already.
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) {
		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println("Can't add new stock, portfolio can only have " + MAX_PORTFOLIO_SIZE + " stocks.");
			return;
		}
		
		for(int i = 0;i<portfolioSize; i++){
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				System.out.println("Stock " + stocks[i].getSymbol() + " already exists.");
				return;
			}
		}
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}

	/**
	 * the process of buying stocks:
	 * 1. calculate price(quantity*ask).
	 * *if it's not in portfolio-add it using addStock().
	 * 2. increase the stock's quantity.
	 * 3. decrease balance with price.
	 * 
	 * @param stock
	 * @param quantity
	 * @return if action succeeded or not.
	 */
	public boolean buyStock(StockInterface stock, int quantity) {
		float totalPrice;
		int i=0;
		totalPrice = quantity * stock.getAsk();
		
		// Check if valid quantity
		if (quantity <= 0) {
			System.out.println("can't buy a negative number of stocks");
			return false;
		}
		if (totalPrice > balance) {
			System.out.println("can't buy the stock-not enough balance to complete purchase.");
			return false;
		}
	
		// calculate maximum quantity you can buy.
		if (quantity == ALL_STOCKS) {
			quantity = (int) (balance / stock.getAsk());
		}
	
		// Case 2: the stock exists-add quantity.
		for(;i<portfolioSize; i++){
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				((Stock) stocks[i]).updateStockQuantity(quantity);
				System.out.println("Added " + quantity + " to " + stocks[i].getSymbol() + " stock.");
				updateBalance(-totalPrice);
				return true;
			}
		}
			
		// Case 3: stock not in portfolio-add new.
		addStock((Stock) stock);
		
		if (i == MAX_PORTFOLIO_SIZE) {
			return false;
		}
		
		((Stock) stocks[i]).updateStockQuantity(quantity);
		System.out.println("added " + stocks[i].getSymbol() + " to portfolio with " + quantity + " stocks. you paid "
				+ totalPrice + "$.");
		updateBalance(-totalPrice);
		return true;
	}

	/**
	 * remove a stock from portfolio:
	 * 1. sell all stocks using sellStock()
	 * 2. remove the stock from the portfolio using overriding.
	 * 
	 * @param symbol- the stock to remove.
	 * @return if action succeeded or not.
	 */
	public boolean removeStock(String symbol) {
		for(int i=0; i< portfolioSize ; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				sellStock(symbol, ALL_STOCKS);
				
				// remove by overriding. the last stock still appear twice and must be deleted after.
				for (int j = i; j < portfolioSize -1 ; j++) {
					stocks[j] = stocks[j + 1];
				}
				stocks[portfolioSize-1]=null;
				portfolioSize--;
				System.out.println("The stock " + symbol + " was removed from portfolio.");
				return true;
			}
		}
		System.out.println("The stock " + symbol + " doesn't exist in portfolio.");
		return false;
	}

	/**
	 * the process of selling stocks:
	 * 1. check if selling all stocks or a specific amount.
	 * 2. decrease the stock's quantity.
	 * 3. increase balance with profit(quantity*bid).
	 * 
	 * @param symbol- the stock to sell
	 * @param quantity- the amount of the stock to sell.
	 * @return if action succeeded or not.
	 */
	public boolean sellStock(String symbol, int quantity) {
		int currentQuantity = 0;
		float profit = 0;
				
		for(int i=0; i< portfolioSize ; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				if (quantity == ALL_STOCKS) {
					currentQuantity = ((Stock) stocks[i]).getStockQuantity();
					profit = currentQuantity * stocks[i].getBid();
					((Stock) stocks[i]).updateStockQuantity(-currentQuantity);
					System.out.println("Sold All " + currentQuantity + " " + stocks[i].getSymbol()
							+ " stocks quantity. you earned " + profit + "$.");
					updateBalance(profit);
					return true;
				} else if (quantity < 0) {
					System.out.println("Can not sell negative quantiy.");
					return false;
				} else if (((Stock) stocks[i]).getStockQuantity() < quantity) {
					System.out.println("You don't have that much stocks to sell.");
					return false;
				} else {
					profit = quantity * stocks[i].getBid();
					((Stock) stocks[i]).updateStockQuantity(-quantity);
					System.out.println("You sold " + quantity + " of " + stocks[i].getSymbol() + " stock.");
					updateBalance(profit);
					return true;
				}
			}
		}

		System.out.println("The stock " + symbol + " doesn't exist in portfolio.");
		return false;
	}

	/**
	 * the market value(bid) of stock can change.
	 * 
	 * @param stockNum
	 * @param bid
	 */
	public void changeStockBid(int stockNum, float bid) {
			if (stocks[stockNum - 1]!= null && bid>0) {
				((Stock) stocks[stockNum - 1]).setBid(bid);
			}
			System.out.println("Bid changed");		
	}

	/**
	 * the total value of stocks in the market.
	 * 
	 * @return float
	 */
	public float getStocksValue() {
		int sum = 0;
		for (int i = 0; i < portfolioSize; i++) {
			sum += ((Stock) stocks[i]).getStockQuantity() * stocks[i].getBid();
		}
		return sum;
	}

	/**
	 * the value of the portfolio.
	 * 
	 * @return float
	 */
	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}
	
	/**
	 * created String with all the portfolio data.
	 * 
	 * @return String
	 */
	public String getHtmlString() {
		String getHtmlPortfolio = " <h1> Portfolio Title: " + getTitle() + " </h1> " + "<b>Total Portfolio Value:</b> "
				+ getTotalValue() + "$<br>" + "<b>Total Stocks value:</b> " + getStocksValue() + "$<br>"
				+ "<b>Balance:</b> " + getBalance() + "$. <br><br>";

		for (int i = 0; i < portfolioSize; i++) {
			getHtmlPortfolio += ((Stock) stocks[i]).getHtmlDescription() + "<br>";
		}
		return getHtmlPortfolio;
	}

	public StockInterface findStock(String symbol) {
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (stocks[i].getSymbol().equals(symbol)) 
					return this.stocks[i];
		}
		return null;
	}
}