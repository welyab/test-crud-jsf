package com.welyab.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.welyab.test.domain.model.Empresa;

@WebServlet("/soma")
public class SomaServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("a");
		String b = req.getParameter("b");

		Empresa empresa = new Empresa();
		
		String nome = "Lucas";
		int s = (Integer.parseInt(a) + Integer.parseInt(b) / 4);
		PrintWriter w = resp.getWriter();
	}
}
