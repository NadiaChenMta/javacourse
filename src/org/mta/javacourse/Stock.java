package org.mta.javacourse;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Stock {
	private String symbol;
	private Float ask;
	private Float bid;
	private Date date;
		
	public Stock()
	{
		date=new java.util.Date();
		symbol="unknown";	
		ask=null;
		bid=null;
	}
	public void setAll(String symbol, Float ask, Float bid, Date date)
	{
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
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
	public String getDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String sDate;
		sDate=df.format(date);
		return sDate;
	}
	public void setDate(java.util.Date date) {
		this.date=date;
     }
	
	
	public String getHtmlDescription() {
		String resString= "<b>Stock symbol</b>: "+getSymbol()+"<b>Ask</b>: " + getAsk()+"<b>Bid</b>: "
	+ getBid()+ "<b>Date</b>: " + getDate()+"<br>";
		return resString;
	}

	}
	
	

