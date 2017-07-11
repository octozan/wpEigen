package de.fom.wp.controller;

import java.io.*;
import java.sql.*;

import javax.inject.Inject;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import de.fom.wp.dao.*;
import de.fom.wp.persistence.*;

//@WebServlet("/j_security_check")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private PersonDao personDao;
	
	// private static final String loginsql = "select * from wp.person p where
	// p.email = ? and p.passphrase_sha2_salted = sha2(CONCAT(?, salt), 512)";

	@Override
	public void init(ServletConfig config) throws ServletException {

		// DB Verbindungen zur Verf�gung stellen
		try {
			String s = config.getServletContext().getInitParameter("datasource");
			InitialContext initialContext = new InitialContext();
			DataSource wp = (DataSource) initialContext.lookup(s);
			//wp = (DataSource)config.getServletContext().getAttribute("ds");
			//personDao = new JdbcPersonDao(wp);
			//personDao = new JpaPersonDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Person user = personDao.register(request.getParameter("j_name"),request.getParameter("j_username"), request.getParameter("j_password"));

			if (user != null) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/home.jsp");
				return;
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
