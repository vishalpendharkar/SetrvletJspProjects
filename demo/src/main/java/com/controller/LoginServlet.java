package com.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/loginuser")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		try {
			Conn conn=new Conn();
			String q="select * from user where username=? and password=?";
			
			PreparedStatement pst=conn.getConnection().prepareStatement(q);
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rst= pst.executeQuery();
			if(rst.next()) {
				HttpSession session =request.getSession();
				session.setAttribute("user", rst.getString(1));
				response.sendRedirect("welcome.jsp");
			}else {
				PrintWriter pwt=response.getWriter();
				pwt.print("invalid username or password");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}