package org.mta.javacourse.model;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
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
	private Date outputDate;
	private Calendar cal;
	private int stockQuantity=0;
	private static enum ALGO_RECOMMENDATION{BUY,SELL,REMOVE,HOLD};
	private ALGO_RECOMMENDATION recommendation;
		
	/**
	 * constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	public Stock(String symbol, float ask, float bid, String date) {
		this.symbol=symbol;
		this.ask = ask;
		this.bid=bid;
		outputDate = new Date(); 
		cal = Calendar.getInstance();
		setDate(date);
	}
	
	/**
	 * copy constructor
	 * 
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(),stock.getAsk(), stock.getBid(),stock.getFormattedDate(stock.getDate()));
		this.stockQuantity = stock.getstockQuantity();
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

	/**
	 * @param inputDate
	 */
	public void setDate(String inputDate) {		
		SimpleDateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy");
		try {
		    	this.outputDate = formatedDate.parse(inputDate);
		    } catch (ParseException e) {
		    	e.printStackTrace();
		    }
		cal.setTime(outputDate);
	}
	
	public Date getDate() {
		return outputDate;
	}
	
	/**
	 * @param outputDate
	 * @return
	 */
	public String getFormattedDate(Date outputDate) {
		SimpleDateFormat formatedCal = new SimpleDateFormat("MM/dd/yyyy");
		return formatedCal.format(cal.getTime());
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
	 * add to stock quantity(buying and selling stocks).
	 * if 0 set stock quantity to 0.
	 * @param stockQuantity
	 */
	public void updateStockQuantity(int stockQuantity) {
		if(stockQuantity == Portfolio.ALL_STOCKS)
		{
			this.stockQuantity=0;
		}
		else
		this.stockQuantity += stockQuantity;
	}
		
	/**
	 * creates String with the stock data.
	 * @return String
	 */
	public String getHtmlDescription() {
		String resString = "<b>Stock symbol</b>: " + getSymbol() + "<b> Ask</b>: " + getAsk() + "<b> Bid</b>: "
				+ getBid() + "<b> Date</b>: " + getFormattedDate(outputDate) + "<b> Quantity</b>: " + getstockQuantity() +"<br>";
		return resString;
	}

}
