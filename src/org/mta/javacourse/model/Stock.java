package org.mta.javacourse.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * An instance of this class represents a stock.
 * #Variables:
 * symbol(string), ask(int), bid(int), date(Date), stockQuantity(int).
 * #C'tors:
 * 1. public Stock() 
 * 2. public Stock(String symbol, Float ask, Float bid, Date date, int stockQuantity)- overloading
 * 3. public Stock(String symbol, Float ask, Float bid, int stockQuantity)- overloading for copy c'tor. can't get date(mutable)
 * 4. public Stock(Stock stock) - copy of stock 
 * #Methods:
 * 1. getters and setters
 * 2. public String dateToStr(Date date)
 * 3. public String getHtmlDescription()
 * 4. public void updateStockQuantity(int stockQuantity) {
 * 
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 13/12/15
 */
public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int stockQuantity;
	private static enum ALGO_RECOMMENDATION{BUY,SELL,REMOVE,HOLD};
	private ALGO_RECOMMENDATION recommendation;
	
	
	/**
	 * constructor
	 */
	public Stock()
	{
		date = new java.util.Date();
		symbol = "unknown";
		stockQuantity=0;
	}

	/**
	 * overloading constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 * @param stockQuantity
	 */
	public Stock(String symbol, Float ask, Float bid, Date date) 
	{
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
		stockQuantity=0;
	}

	/**
	 * overloading constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 */
	public Stock(String symbol, Float ask, Float bid, int stockQuantity) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		updateStockQuantity(stockQuantity);
	}

	/**
	 * copy constructor
	 * 
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getstockQuantity()); // immutable
		this.date = new java.util.Date(stock.getDate().getTime()); // mutable
	}

	//getters and setters
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Float getAsk() {
		return ask;
	}

	public void setAsk(Float ask) {
		this.ask = ask;
	}

	public Float getBid() {
		return bid;
	}

	public void setBid(Float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public int getstockQuantity() {
		return stockQuantity;
	}
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	
	/**
	 * add to stock quantity. if 0 set stock quantity to 0
	 * @param stockQuantity
	 */
	public void updateStockQuantity(int stockQuantity) {
		if(stockQuantity == 0)
		{
			this.stockQuantity=0;
		}
		else
		this.stockQuantity += stockQuantity;
	}
	
	/**
	 * convert date to string format.
	 * 
	 * @return date as a string
	 */	
	public String dateToStr(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String sDate;
		sDate = df.format(date);
		return sDate;
	}

	
	/**
	 * creates String with the stock data.
	 * @return String
	 */
	public String getHtmlDescription() {
		String resString = "<b>Stock symbol</b>: " + getSymbol() + "<b> Ask</b>: " + getAsk() + "<b> Bid</b>: "
				+ getBid() + "<b> Date</b>: " + dateToStr(date) + "<b> Quantity</b>: " + getstockQuantity() +"<br>";
		return resString;
	}

}
