package org.mta.javacourse;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Stock {
	private String symbol;
	private Float ask;
	private Float bid;
	private java.util.Date date;
		//java.util.Date date=new java.util.Date();//
	public Stock()
	{
		java.util.Date date=new java.util.Date();
		symbol="unknown";
		ask=(float) -1;
		bid=(float) -1;		
	}
	public void setAll(String symbol, Float ask, Float bid)
	{
		setSymbol(this.symbol);
		setAsk(this.ask);
		setBid(this.bid);
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
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-MM-yyyy");

        String strdate2 = "11-15-2014";
        try {

            date = dateformat2.parse(strdate2);

            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();

        }

	}
	
	public String getHtmlDescription() {
		String resString= "<b>Stock symbol</b>: "+getSymbol()+"<b>Ask</b>: " + getAsk()+"<b>Bid</b>: " + getBid()+ "<b>Date</b>: " + getDate();
		return resString;
	}
	
	
}
