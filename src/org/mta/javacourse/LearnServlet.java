package org.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;
// we can do import to the class And it is hot

@SuppressWarnings("serial")
public class LearnServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		int x=1;
		int y=1;
		String result= x+" + "+y+"="+(x+y);
		
		resp.getWriter().println(result);
	}
}
