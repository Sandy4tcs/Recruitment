package com.Web;




import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;




@WebService()
@Entity
@Path("/submitdata")
public class PostJobWs {

    String query="";
    @POST
    @Path("submit") 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/plain")
    @WebMethod(operationName = "submit")
	public void submitData(@FormParam("jobtitle") String jobtitle,
							@FormParam("location") String location,
							@FormParam("miles") String miles,
							@FormParam("salary") String salary,
							@FormParam("jobtype") String jobtype,
							@FormParam("jobtime") String jobtime,
							@FormParam("degree") String degree,
							@FormParam("degreeSubject") String degreeSubject,
							@FormParam("institution") String institution,
							@FormParam("jobexp") String jobexp,
							@FormParam("licence") String licence,
							@FormParam("carowner") String carowner,
							@Context HttpServletResponse servletResponse) throws IOException
	{
    	
		PreparedStatement st = null;
    	Connection connection=null;
		DBconnection database= new DBconnection();
		try {
			connection = database.getConnection();
			query= "INSERT INTO JOBPROFILES (JOBTITLE,LOCATION,MILES,SALARY,JOBTYPE,JOBTIME,DEGREE,SUBJECT,INSTITUTION,JOB_EXPERIENCE,DRIVING_LICENCE,CAR_OWNER) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			st=connection.prepareStatement(query);
			st.setString(1,jobtitle);
			st.setString(2, location);
			st.setString(3, miles);
			st.setString(4, salary);
			st.setString(5, jobtype);
			st.setString(6, jobtime);
			st.setString(7, degree);
			st.setString(8, degreeSubject);
			st.setString(9, institution);
			st.setString(10, jobexp);
			st.setString(11, licence);
			st.setString(12, carowner);
			
			
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
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
