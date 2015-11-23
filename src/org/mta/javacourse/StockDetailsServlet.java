package org.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("hello, world");
		
		Stock stock1=new Stock();
		Stock stock2=new Stock();
		Stock stock3=new Stock();
		
		stock1.setAll("B", (float)1,(float)2);
		stock2.setAll("c", (float)1,(float)5);
		stock3.setAll("d", (float)1,(float)4);

		
		stock1.getHtmlDescription();
		stock2.getHtmlDescription();
		stock3.getHtmlDescription();
	}
}
