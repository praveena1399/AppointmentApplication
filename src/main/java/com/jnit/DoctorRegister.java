package com.jnit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DoctorRegister extends HttpServlet{
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
			String name= request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String mobile = request.getParameter("phone");
			long phone = Long.parseLong(mobile);
			String specialization = request.getParameter("specialization");
			
			PrintWriter pw = response.getWriter();
			String sql = "insert into doctor(name, password, email,phone,specialization) values(?,?,?,?,?)";
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setLong(4, phone);
				ps.setString(5, specialization);
				int x=ps.executeUpdate();
//				pw.println("<html><body bgcolor='wheat'><h1 align='center'>");
				if(x!=0) {
//					pw.println("Doctor registered succesfully!");
					response.sendRedirect("./doctor_login.html");
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
