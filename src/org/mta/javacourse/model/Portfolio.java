package org.mta.javacourse.model;

/**
 * An instance of this class represents portfolio that contains stocks.
 * #Variables: title(string), i(int), portfolioSize(int), stocks[](stock array).
 * #C'tors: 1. public Portfolio() 2. public Portfolio(Portfolio portfolio) -
 * copy of portfolio #Methods: 1. getters and setters 2. public void
 * addStock(Stock stock) 3. public void removeStock(int stockNum) 4. public void
 * changeStockBid(int stockNum, float bid) 5. public String getHtmlPortfolio()
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 7/12/15
 */
public class Portfolio {
	public static final int MAX_PORTFOLIO_SIZE = 5;
	public static final int ALL_STOCKS = -1;
	private String title;
	private int i;
	private float balance;
	Stock[] stocks;
	int portfolioSize;

	/**
	 * constructor
	 */
	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = "unknown";
		portfolioSize = 0;
	}

	/**
	 * copy constructor
	 * 
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.portfolioSize;

		for (i = 0; i < portfolio.portfolioSize; i++) {
			stocks[i] = new Stock((portfolio.stocks[i]));
		}

	}

	public float getBalance() {
		return balance;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * add stock to portfolio.
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) {
		i = 0;

		while (stocks[i] != null) {
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				System.out.println("Stock " + stocks[i].getSymbol() + " already exists.");
				return;
			}

			if (portfolioSize == MAX_PORTFOLIO_SIZE) {
				System.out.println("Can't add new stock, portfolio can only have " + MAX_PORTFOLIO_SIZE + " stocks.");
				return;
			}
			i++;
		}

		stocks[portfolioSize] = stock;
		portfolioSize++;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	/**
	 * remove a stock from portfolio.
	 * 
	 * @param stockNum
	 */
	public boolean removeStock(String symbol) {
		for (i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				sellStock(symbol, ALL_STOCKS);

				for (; i < portfolioSize - 1; i++) {
					stocks[i] = stocks[i + 1];
				}

				portfolioSize--;
				System.out.println("The stock " + stocks[i].getSymbol() + " was removed from portfolio.");
				return true;
			}
		}

		System.out.println("The stock " + stocks[i].getSymbol() + " doesn't exist in portfolio.");
		return false;

	}

	/**
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean sellStock(String symbol, int quantity) {
		float currentQuantity = 0;
		float totalPrice = 0;

		for (i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				if (quantity == ALL_STOCKS) {
					currentQuantity = stocks[i].getstockQuantity();
					totalPrice = currentQuantity * stocks[i].getBid();
					stocks[i].updateStockQuantity(ALL_STOCKS);
					System.out.println("Sold All " + currentQuantity + " " + stocks[i].getSymbol()
							+ " stocks quantity. you earned " + totalPrice + "$.");
					updateBalance(totalPrice);
					return true;
				} else if (quantity < 0) {
					System.out.println("Can not sell negative quantiy.");
					return false;
				} else if (stocks[i].getstockQuantity() < quantity) {
					System.out.println("You don't have that much stocks to sell.");
					return false;
				} else {
					totalPrice = quantity * stocks[i].getBid();
					stocks[i].updateStockQuantity(quantity);
					System.out.println("You sold " + quantity + " of " + stocks[i].getSymbol() + " stock.");
					updateBalance(totalPrice);
					return true;
				}
			}
		}

		System.out.println("The stock" + stocks[i].getSymbol() + " doesn't exist in portfolio.");
		return false;
	}

	/**
	 * @param stock
	 * @param quantity
	 * @return
	 */
	public boolean buyStock(Stock stock, int quantity) {
		float totalPrice = quantity * stock.getAsk();

		// Check if valid quantity
		if (quantity <= 0) {
			System.out.println("can't buy a negative number of stocks");
			return false;
		}
		if (totalPrice > balance) {
			System.out.println("not enough money in your balnce. can't buy a stock");
			return false;
		}

		// calculate maximum quantity you can buy.
		if (quantity == ALL_STOCKS) {
			quantity = (int) (balance / stock.getAsk());
		}

		// Case 2: the stock exists-add quantity.
		for (i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(stock.getSymbol())) {
				stocks[i].updateStockQuantity(quantity);
				updateBalance(-totalPrice);
				System.out.println("Added " + quantity + " to " + stocks[i].getSymbol() + " stock.");
				return true;
			}
		}

		// Case 3: stock not in portfolio-add new.
		addStock(stock);
		stocks[i].updateStockQuantity(quantity);
		System.out.println("added " + stocks[i].getSymbol() + " to portfolio with " + quantity + " stocks. you paid "
				+ totalPrice + "$.");
		updateBalance(-totalPrice);
		return true;
	}

	/**
	 * change a stock bid value.
	 * 
	 * @param stockNum
	 * @param bid
	 */
	public void changeStockBid(int stockNum, float bid) {
		stocks[stockNum - 1].setBid(bid);
	}

	
	/**
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
	 * @return
	 */
	public float getStocksValue() {
		int sum = 0;
		for (i = 0; i < portfolioSize; i++) {
			sum += stocks[i].getstockQuantity() * stocks[i].getAsk();
		}
		return sum;
	}

	/**
	 * @return
	 */
	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}

	/**
	 * @return
	 */
	public String getHtmlPortfolio() {
		String getHtmlPortfolio = " <h1> Portfolio Title: " + getTitle() + " </h1> " + "<b>Total Portfolio Value:</b> "
				+ getTotalValue() + "$<br>" + "<b>Total Stocks value:</b> " + getStocksValue() + "$<br>"
				+ "<b>Balance:</b> " + getBalance() + "$. <br><br>";

		for (i = 0; i < portfolioSize; i++) {
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}
		return getHtmlPortfolio;
	}
}