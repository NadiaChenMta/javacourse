package org.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
import java.lang.String;

@SuppressWarnings("serial")
public class Exercise3 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
				
		CalculationManager calculator = new CalculationManager();
		String resultStr = calculator.getResults();
		resp.getWriter().println(resultStr);
	}
}
