package org.mta.javacourse;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		Date date = null;

	    String expectedPattern = "MM/dd/yyyy";
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
	    String userInput = "11/15/2014";
	     try {
			date= formatter.parse(userInput);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		Stock stock1=new Stock();
		Stock stock2=new Stock();
		Stock stock3=new Stock();
		
		stock1.setAll("PIH",(float)13.1,(float)12.4,date);		
		stock2.setAll("AAL", (float)5.78,(float)5.5,date);
		stock3.setAll("CAAS", (float)32.2,(float)31.5,date);
		
		
		String resString = stock1.getHtmlDescription()+stock2.getHtmlDescription()+stock3.getHtmlDescription();
		resp.getWriter().println(resString);
		
	}
}
	
