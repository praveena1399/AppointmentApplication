package com.jnit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DoctorLogin extends HttpServlet{
	Connection connection = null;
	PreparedStatement ps = null;
		public void init(ServletConfig config) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String url = "jdbc:mysql://localhost:3307/jnit";
			 String username = "root";
			 String password = "root";
			 try {
				connection = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			PrintWriter pw = response.getWriter();
			String sql = "select * from doctor where email=? and password =?";
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(2, password);
				ps.setString(1, email);
				ResultSet rs=ps.executeQuery();
//				pw.println("<html><body bgcolor='wheat'><h1 align='center'>");
				if(rs.next()) {
//					pw.println("Doctor login succesful!");
					response.sendRedirect("./doctor_home.html");
				}
//				pw.println("</h1></body></html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void destroy() {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
