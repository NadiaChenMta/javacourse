package org.mta.javacourse.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock {
	public static final int BUY = 0;
	public static final int SELL = 1;
	public static final int REMOVE = 2;
	public static final int HOLD = 3;

	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;

	/**
	 * constructor
	 */
	public Stock() {
		date = new java.util.Date();
		symbol = "unknown";
	}

	/**
	 * overloading constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	public Stock(String symbol, Float ask, Float bid, Date date) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
	}

	/**
	 * overloading constructor
	 * 
	 * @param symbol
	 * @param ask
	 * @param bid
	 */
	public Stock(String symbol, Float ask, Float bid) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
	}

	/**
	 * copy constructor
	 * 
	 * @param stock
	 */
	public Stock(Stock stock) {
		this(stock.getSymbol(), stock.getAsk(), stock.getBid()); // immutable
		this.date = new java.util.Date(stock.getDate().getTime()); // mmutable
	}

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

	/**
	 * convert date to string format.
	 * 
	 * @return date as a string
	 */
	public String dateToStr(Date date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String sDate;
		sDate = df.format(date);
		return sDate;
	}

	public String getHtmlDescription() {
		String resString = "<b>Stock symbol</b>: " + getSymbol() + "<b> Ask</b>: " + getAsk() + "<b> Bid</b>: "
				+ getBid() + "<b> Date</b>: " + dateToStr(date) + "<br>";
		return resString;
	}

}
