package com.platzerworld.weihnachtsmarkt;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class WeihnachtsmarktServiceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("WeichnachtsmarktService is running!");
	}
}
