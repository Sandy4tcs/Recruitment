package com.Web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.beans.CandidateDetails;
import com.google.gson.Gson;

@WebService()
@Entity
@Path("/searchAll")
public class SearchAllWs {
	
	String query="";
	@GET
	@Path("search")
	@Produces("text/plain")
	@WebMethod(operationName = "searchall")
	public String getAllList(@QueryParam("key") String key)
	{
		ArrayList<CandidateDetails> list=new ArrayList<CandidateDetails>();
		DBconnection database=new DBconnection();
		Connection connection=null;
		ResultSet rs = null;
		String json="";
		PreparedStatement st = null;
		
		try {
			connection = database.getConnection();
			query="SELECT * FROM CANDIDATEPROFILES where FNAME like \'%"+ key +"%\' or LNAME like \'%"+ key +"%' or GENDER like \'%"+ key +"%\' or ADDRESS like \'%"+ key +"%\' or "
					+ "DEGREE like \'%"+ key +"%\' or "
					+ "SUBJECT like \'%"+ key +"%\' or "
					+ "UNIVERSITY like \'%"+ key +"%\' or "
					+ "JOB_EXPERIENCE like \'%"+ key +"%\' or "
					+ "PHONENO like \'%"+ key +"%\' or "
					+ "SKILL1 like \'%"+ key +"%\' or "
					+ "SKILL2 like \'%"+ key +"%\' or "
					+ "SKILL like \'%"+ key +"%\' or "
					+ "DRIVING_LICENCE like \'%"+ key +"%\' or "
					+ "CAR_OWNER like \'%"+ key +"%\' or "
					+ "MILES like \'%"+ key +"%\' or "
					+ "EMAIL like \'%"+ key +"%\'";
			st=connection.prepareStatement(query);
			rs=st.executeQuery();
			
			while (rs.next()) {
				CandidateDetails cd=new CandidateDetails();
				cd.setFirstName(rs.getString("FNAME"));
				cd.setLastName(rs.getString("LNAME"));
				cd.setGender(rs.getString("GENDER"));
				cd.setAddress(rs.getString("ADDRESS"));
				cd.setDegree(rs.getString("DEGREE"));
				cd.setSubject(rs.getString("SUBJECT"));
				cd.setUniversity(rs.getString("UNIVERSITY"));
				cd.setExperience(rs.getString("JOB_EXPERIENCE"));
				cd.setPhoneNo(rs.getString("PHONENO"));
				cd.setSkill1(rs.getString("SKILL1"));
				cd.setSkill2(rs.getString("SKILL2"));
				cd.setSkill(rs.getString("SKILL"));
				cd.setDrivinglicence(rs.getString("DRIVING_LICENCE"));
				cd.setCarowner(rs.getString("CAR_OWNER"));
				cd.setMiles(rs.getString("MILES"));
				cd.setEmail(rs.getString("EMAIL"));
				list.add(cd);
				json=new Gson().toJson(list);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
