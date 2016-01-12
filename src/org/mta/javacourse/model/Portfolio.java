package org.mta.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
import org.mta.javacourse.exception.BalanceException;
import org.mta.javacourse.exception.NotEnoughStocksException;
import org.mta.javacourse.exception.PortfolioFullException;
import org.mta.javacourse.exception.NotValidQuantityException;
import org.mta.javacourse.exception.StockAlreadyExistsException;
import org.mta.javacourse.exception.StockNotExistException;

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
/**
 * @author nadia
 *
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
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
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
	public void updateBalance(float amount) throws BalanceException{
		if (this.balance + amount < 0) {
			throw new BalanceException();
		}
		
		//System.out.println("Balance updated with " + amount + "$");
		balance += amount;
		
	}

	/**
	 * add stock to portfolio if it doesn't exist and the portfolio is not full already.
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) throws PortfolioFullException,StockAlreadyExistsException {
		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			throw new PortfolioFullException(MAX_PORTFOLIO_SIZE);
		}
		
		for(int i = 0;i<portfolioSize; i++){
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				throw new StockAlreadyExistsException(stocks[i].getSymbol());
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
	 * @throws BalanceException 
	 * @throws StockAlreadyExistsException 
	 */
	public void buyStock(StockInterface stock, int quantity) throws BalanceException,PortfolioFullException, StockAlreadyExistsException, NotValidQuantityException {
		float totalPrice;
		int i=0;
		totalPrice = quantity * stock.getAsk();
		boolean stockExists=false;
		
		// Check if valid quantity
		if (quantity <= 0 && quantity!=ALL_STOCKS) {
			throw new NotValidQuantityException();
		}
		if (totalPrice > balance) {
			throw new BalanceException();
			//"can't buy the stock-not enough balance to complete purchase."
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
				stockExists = true;
				break;
			}
		}
			
		// Case 3: stock not in portfolio-add new.
		if (!stockExists) {
			try {
				addStock((Stock) stock);
			} catch (PortfolioFullException e) {
				throw e; // Propagation
			}
			catch(StockAlreadyExistsException e){
				throw e; // Propagation
			}
			((Stock) stocks[i]).updateStockQuantity(quantity);
			System.out.println("added " + stocks[i].getSymbol() + " to portfolio with " + quantity + " stocks. you paid "+ totalPrice + "$.");
			updateBalance(-totalPrice);
		}	
	}

	/**
	 * remove a stock from portfolio:
	 * 1. sell all stocks using sellStock()
	 * 2. remove the stock from the portfolio using overriding.
	 * 
	 * @param symbol- the stock to remove.
	 * @return if action succeeded or not.
	 * @throws BalanceException 
	 * @throws NotEnoughStocksException 
	 * @throws NotValidQuantityException 
	 */
	public void removeStock(String symbol) throws BalanceException,StockNotExistException, NotValidQuantityException, NotEnoughStocksException {
		boolean stockExists=false;
		
		for(int i=0; i< portfolioSize ; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				try {
					sellStock(symbol, ALL_STOCKS); 
				} catch (NotValidQuantityException e) {
					throw e; // Propagation
				} 
				catch (NotEnoughStocksException e) {
					throw e; // Propagation
				} 
				catch (StockNotExistException e) {
					throw e; // Propagation
				} 
				
				// remove by overriding. the last stock still appear twice and must be deleted after.
				for (int j = i; j < portfolioSize -1 ; j++) {
					stocks[j] = stocks[j + 1];
				}
				stocks[portfolioSize-1]=null;
				portfolioSize--;
				System.out.println("The stock " + symbol + " was removed from portfolio.");
				stockExists=true;
				break;
			}
		}
		if (!stockExists) {
			throw new StockNotExistException(symbol);
		}
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
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 * @throws NotValidQuantityException 
	 * @throws NotEnoughStocksException 
	 */
	public void sellStock(String symbol, int quantity) throws BalanceException, StockNotExistException, NotValidQuantityException, NotEnoughStocksException {
		int currentQuantity = 0;
		float profit = 0;
		boolean stockExists=false;		
		
		for(int i=0; i< portfolioSize ; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				if (quantity == ALL_STOCKS) {
					currentQuantity = ((Stock) stocks[i]).getStockQuantity();
					profit = currentQuantity * stocks[i].getBid();
					((Stock) stocks[i]).updateStockQuantity(-currentQuantity);
					System.out.println("Sold All " + currentQuantity + " " + stocks[i].getSymbol()
							+ " stocks quantity. you earned " + profit + "$.");
					updateBalance(profit);
				} else if (quantity < 0) {
					throw new NotValidQuantityException();
				} else if (((Stock) stocks[i]).getStockQuantity() < quantity) {
					throw new NotEnoughStocksException();
				} else {
					profit = quantity * stocks[i].getBid();
					((Stock) stocks[i]).updateStockQuantity(-quantity);
					System.out.println("You sold " + quantity + " of " + stocks[i].getSymbol() + " stock.");
					updateBalance(profit);
				}
				stockExists=true;
				break;
			}
		}
		
		if (!stockExists) {
			throw new StockNotExistException(symbol);
		}
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

	/**
	 * find a specific stock in portfolio.
	 * @param symbol
	 * @return stock object
	 */
	public StockInterface findStock(String symbol) {
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (stocks[i].getSymbol().equals(symbol)) 
					return this.stocks[i];
		}
		return null;
	}
}