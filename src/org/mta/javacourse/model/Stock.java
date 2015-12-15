package org.mta.javacourse.model;

import java.util.Calendar;
import java.util.Date;

import org.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * An instance of this class represents a stock.
 * #Variables:
 * symbol(string), ask(int), bid(int), outputDate(Date), cal(Calendar), stockQuantity(int), recommendation(ALGO_RECOMMENDATION).
 * 
 * #C'tors:
 * 1. public Stock() 
 * 2. public Stock(Stock stock)
 * 
 * #Methods:
 * 1. getters and setters
 * 2. public String getFormattedDate()
 * 3. public String getHtmlDescription()
 *   
 * @author Chen Mualem & Nadia Medvedovsky
 * @since 2015
 * @date 14/12/15
 */
public class Stock {
	private String symbol;
	private float ask;
	private float bid;
	private Date outputDate;
	private Calendar cal;
	private int stockQuantity = 0;
	private ALGO_RECOMMENDATION recommendation;
	
	/**
	 * constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date- String input is changed to date type.
	 */
	public Stock(String symbol, float ask, float bid, String date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		// date is immutable, so it must be created anew.
		outputDate = new Date();
		cal = Calendar.getInstance();
		setDate(date);
	}

	/**
	 * copy constructor. note:
	 * date is formatted to String like in the default constructor input. 
	 * default constructor doesn't have quantity input because only existing stocks have quantity.
	 * 
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getFormattedDate());
		this.stockQuantity = stock.getStockQuantity();
	}

	// getters
	public String getSymbol() {
		return symbol;
	}

	public float getAsk() {
		return ask;
	}

	public float getBid() {
		return bid;
	}

	public Date getDate() {
		return outputDate;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	// setters
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setAsk(float ask) {
		if(bid < 0){
			System.out.println("Ask can't be negetive.");
		} else {
		this.ask = ask;
		}
	}

	public void setBid(float bid) {
		if(bid < 0){
			System.out.println("Bid can't be negetive.");
		} else {
		this.bid = bid;
		}
	}

	/**
	 * date setter needs to convert String to date type.
	 * 
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

	/**
	 * quantity setter only updates to prevent misuse. add to stock
	 * quantity(buying and selling stocks).
	 * 
	 * @param currentQuantity
	 */
	public void updateStockQuantity(float currentQuantity) {
		this.stockQuantity += currentQuantity;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	// Methods
	/**
	 * convert date to String format.
	 * 
	 * @return date as String
	 */
	public String getFormattedDate() {
		SimpleDateFormat formatedCal = new SimpleDateFormat("MM/dd/yyyy");
		return formatedCal.format(cal.getTime());
	}

	/**
	 * creates String with the stock data.
	 * 
	 * @return String
	 */
	public String getHtmlDescription() {
		String resString = "<b>Stock symbol</b>: " + getSymbol() + "<b> Ask</b>: " + getAsk() + "<b> Bid</b>: "
				+ getBid() + "<b> Date</b>: " + getFormattedDate() + "<b> Quantity</b>: " + getStockQuantity() + "<br>";
		return resString;
	}

}