package de.fom.wp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.fom.wp.dao.PersonDao;
import de.fom.wp.dao.TestPersonDao;
import de.fom.wp.persistence.Person;

@WebServlet(urlPatterns="/api/*")
public class ApiServlet extends HttpServlet {

	PersonDao dao = new TestPersonDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		if(req.getRequestURI().endsWith("/persons")){
			List<Person> list = dao.list();
			Gson gson = new GsonBuilder().create();
			gson.toJson(list, resp.getWriter());
		}
	}

	
	
}
