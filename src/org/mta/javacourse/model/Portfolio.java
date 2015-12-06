package org.mta.javacourse.model;


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
		
		for(int i = 0; i < portfolio.portfolioSize ; i++){
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
	 * change a stock "bid" value.
	 * @param stockNum
	 * @param bid
	 */
	public void changeStockBid(int stockNum, float bid){
		stocks[stockNum-1].setBid(bid);
	}
	
	
	/**
	 * print the protfolio's stocks details.
	 * @return
	 */
	public String getHtmlPortfolio(){	
		String getHtmlPortfolio = " <h1> Portfolio Title: " +getTitle()+" </h1>";
		
		for(i = 0 ;i < portfolioSize; i++){
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}

		return getHtmlPortfolio;
	}

}