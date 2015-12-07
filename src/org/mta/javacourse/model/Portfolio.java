package org.mta.javacourse.model;


/**
 * An instance of this class represents portfolio that contains stocks.
 * #Variables:
 * title(string), i(int), portfolioSize(int), stocks[](stock array).
 * #C'tors:
 * 1. public Portfolio()
 * 2. public Portfolio(Portfolio portfolio) - copy of portfolio
 * #Methods:
 * 1. getters and setters
 * 2. public void addStock(Stock stock)
 * 3. public void removeStock(int stockNum)
 * 4. public void changeStockBid(int stockNum, float bid)
 * 5. public String getHtmlPortfolio()
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 7/12/15
 */
public class Portfolio {
	public static final int MAX_PORTFOLIO_SIZE=5;
	private String title;
	private int i;
	Stock[] stocks;
	int portfolioSize;
	
	
	/**
	 * constructor
	 */
	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = "unknown";
		portfolioSize = 0;
	}
	
	/**
	 * copy constructor
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.portfolioSize;
		
		for(i = 0; i < portfolio.portfolioSize ; i++){
			stocks[i] = new Stock((portfolio.stocks[i]));
		}
		
	}
	
	public void setTitle(String title) {
		this.title =title;
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * add stock to portfolio.
	 * @param stock
	 */
	public void addStock(Stock stock){
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public Stock[] getStocks(){
		return stocks;
	}
	
	/**
	 * remove a stock from portfolio.
	 * @param stockNum
	 */
	public void removeStock(int stockNum) {
		if(stockNum == portfolioSize)
			this.portfolioSize--;
		else 
		{
			this.portfolioSize--;
			for(int i = stockNum; i <= portfolioSize-1; i++)
			{
				this.stocks[i] = this.stocks[i+1];
			}
		}
	}
	
	/**
	 * change a stock bid value.
	 * @param stockNum
	 * @param bid
	 */
	public void changeStockBid(int stockNum, float bid){
		stocks[stockNum-1].setBid(bid);
	}
	
	/**
	 * creates String with all the protfolio's stocks data.
	 * @return String
	 */
	public String getHtmlPortfolio(){	
		String getHtmlPortfolio = " <h1> Portfolio Title: " +getTitle()+" </h1>";
		
		for(i = 0 ;i < portfolioSize; i++){
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}

		return getHtmlPortfolio;
	}
}