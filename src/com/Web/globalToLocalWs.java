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
@Path("/addlocal")
public class globalToLocalWs {
	
	String query1="";
	String query2="";
	@GET
	@Path("local")
	@Produces("text/plain")
	@WebMethod(operationName = "searchlocal")
	public void setLocalProfiles(@QueryParam("data") String key)
	{
		DBconnection database=new DBconnection();
		Connection connection=null;
		ResultSet rs = null;
		String json="";
		CandidateDetails cd=null;
		PreparedStatement st = null;
		try {
			connection = database.getConnection();
			query1="SELECT * FROM CANDIDATEPROFILES WHERE FNAME=\'"+key+"\'";
			st=connection.prepareStatement(query1);
			rs=st.executeQuery();
			while (rs.next()) {
				cd=new CandidateDetails();
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
			}
			st.close();
			query2="INSERT INTO LOCALCANDIDATES (FNAME,LNAME,GENDER,ADDRESS,DEGREE,SUBJECT,UNIVERSITY,JOB_EXPERIENCE,PHONENO,SKILL1,SKILL2,SKILL,DRIVING_LICENCE,CAR_OWNER,MILES,EMAIL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			st=connection.prepareStatement(query2);
			st.setString(1, cd.getFirstName());
			st.setString(2, cd.getLastName());
			st.setString(3, cd.getGender());
			st.setString(4, cd.getAddress());
			st.setString(5, cd.getDegree());
			st.setString(6, cd.getSubject());
			st.setString(7, cd.getUniversity());
			st.setString(8, cd.getExperience());
			st.setString(9, cd.getPhoneNo());
			st.setString(10, cd.getSkill1());
			st.setString(11, cd.getSkill2());
			st.setString(12, cd.getSkill());
			st.setString(13, cd.getDrivinglicence());
			st.setString(14, cd.getCarowner());
			st.setString(15, cd.getMiles());
			st.setString(16, cd.getEmail());
			st.executeUpdate();
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
