package com.Web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.beans.JobProfile;

@WebService()
@Entity
@Path("/JobList")
public class jobListWs {
	
	@GET
	@Path("list")
	@Produces("text/plain")
	@WebMethod(operationName = "list")
	public String getJobList()
	{
		ArrayList<JobProfile> jobList=new ArrayList<JobProfile>();
		DBconnection database=new DBconnection();
		Connection connection=null;
		ResultSet rs = null;
		String json="";
		PreparedStatement st = null;
		try {
			connection = database.getConnection();
			String query="SELECT * FROM JOBPROFILES";
			st=connection.prepareStatement(query);
			rs=st.executeQuery();
			while (rs.next()) {
				JobProfile jd=new JobProfile();
				jd.setJobid(rs.getString(1));
				jd.setJobTitle(rs.getString(2));
				jd.setLocation(rs.getString(3));
				jd.setMiles(rs.getString(4));
				jd.setSalary(rs.getString(5));
				jd.setJobType(rs.getString(6));
				jd.setJobTime(rs.getString(7));
				jd.setDegree(rs.getString(8));
				jd.setSubject(rs.getString(9));
				jd.setInstitution(rs.getString(10));
				jd.setJobExp(rs.getString(11));
				jd.setDrivingLicence(rs.getString(12));
				jd.setCarowner(rs.getString(13));
				jobList.add(jd);
			}
			json = new Gson().toJson(jobList);
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
		return json;
	}
	

}
