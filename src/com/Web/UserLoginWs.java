package com.Web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.beans.User;

@WebService()
@Entity
@Path("/web")
public class UserLoginWs {

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@WebMethod(operationName = "login")
	public void login(@FormParam("username") String username,
			 @FormParam("password") String password,@Context HttpServletResponse servletResponse) {
		
		ArrayList<User> userlist=null;
		Connection connection=null;
		DBconnection database= new DBconnection();
		try {
			connection = database.getConnection();
			LoginHandler loginHandler= new LoginHandler();
			userlist= loginHandler.getAllUsers(connection);
			
			for(User u:userlist)
			{
				if(u.getUsername().equals(username) && u.getPassword().equals(password))
				{
					servletResponse.sendRedirect("/Recruitment/index.html");
				}
				else
				{
					servletResponse.sendRedirect("/Recruitment/");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
