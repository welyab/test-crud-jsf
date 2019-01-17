package com.welyab.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/teste")
public class Teste extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter w = resp.getWriter();
		resp.setContentType("text/plain");
		w.println("<html>/n"
				+ "<head<>title>Funcionando...</title></head\n"
				+ "<body>/n"
				+ "<h1>Tudo tranquilo...</h1>"
				+ "<body></html>");
	}
}
