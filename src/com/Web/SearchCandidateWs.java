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
import com.beans.JobProfile;
import com.google.gson.Gson;


@WebService()
@Entity
@Path("/searchcandidate")
public class SearchCandidateWs {
	
	String query1="";
	String query2="";
	
	@GET
	@Path("searchlist")
	@Produces("text/plain")
	@WebMethod(operationName = "searchlist")
	public String getCandidateList(@QueryParam("jobid") String jobid)
	{
		ArrayList<CandidateDetails> candidateList=new ArrayList<CandidateDetails>();
		JobProfile jd=new JobProfile();
		DBconnection database=new DBconnection();
		Connection connection=null;
		ResultSet rs = null;
		String json="";
		PreparedStatement st = null;
		try {
			connection = database.getConnection();
			query1="SELECT * FROM JOBPROFILES WHERE JOBID=?";
			st=connection.prepareStatement(query1);
			st.setString(1,jobid);
			rs=st.executeQuery();
			System.out.println(st.toString());
			while (rs.next()){
				jd.setJobid(rs.getString("JOBID"));
				//System.out.println(rs.getString("JOBID"));
				jd.setJobTitle(rs.getString("JOBTITLE"));
				//System.out.println(rs.getString("JOBTITLE"));
				jd.setLocation(rs.getString("LOCATION"));
				//System.out.println(rs.getString("LOCATION"));
				jd.setMiles(rs.getString("MILES"));
				//System.out.println(rs.getString("MILES"));
				jd.setSalary(rs.getString("SALARY"));
				//System.out.println(rs.getString("SALARY"));
				jd.setJobType(rs.getString("JOBTYPE"));
				//System.out.println(rs.getString("JOBTYPE"));
				jd.setJobTime(rs.getString("JOBTIME"));
				//System.out.println(rs.getString("JOBTIME"));
				jd.setDegree(rs.getString("DEGREE"));
				jd.setSubject(rs.getString("SUBJECT"));
				jd.setInstitution(rs.getString("INSTITUTION"));
				jd.setJobExp(rs.getString("JOB_EXPERIENCE"));
				jd.setDrivingLicence(rs.getString("DRIVING_LICENCE"));
				jd.setCarowner(rs.getString("CAR_OWNER"));
			}
			st.close();
			query2 = "SELECT FNAME,LNAME,GENDER,ADDRESS,DEGREE,SUBJECT,UNIVERSITY,JOB_EXPERIENCE,PHONENO,SKILL1,SKILL2,SKILL,DRIVING_LICENCE,CAR_OWNER,MILES,EMAIL FROM CANDIDATEPROFILES "
					+"WHERE ADDRESS=\'"+ jd.getLocation().toUpperCase().trim() +"\' AND MILES="+jd.getMiles()+" AND DRIVING_LICENCE=\'"+jd.getDrivingLicence().toUpperCase()+"\' AND CAR_OWNER=\'"+jd.getCarowner().toUpperCase()+"\'"
					+" AND JOB_EXPERIENCE=\'"+ jd.getJobExp().toUpperCase() +"\' AND DEGREE=\'"+ jd.getDegree().toUpperCase()+"\' AND SUBJECT=\'"+ jd.getSubject().toUpperCase() +"\' AND UNIVERSITY=\'"+ jd.getInstitution().toUpperCase() +"\'";			
//			if(jd.getMiles()!=null)
//			{
//				query2.concat("WHERE MILES="+jd.getMiles());
//			}
//			if(jd.getLocation()!=null)
//			{
//				query2.concat(" AND ADDRESS="+jd.getLocation());
//			}
//			if(jd.getDegree()!=null)
//			{
//				query2.concat(" AND DEGREE="+jd.getDegree());
//			}
//			if(jd.getSubject()!=null)
//			{
//				query2.concat(" AND SUBJECT="+jd.getSubject());
//			}
//			if(jd.getInstitution()!=null)
//			{
//				query2.concat(" AND UNIVERSITY="+jd.getInstitution());
//			}
//			if(jd.getJobExp()!=null)
//			{
//				query2.concat(" AND JOB_EXPERIENCE="+jd.getJobExp());
//			}
//			if(jd.getDrivingLicence()!=null)
//			{
//				query2.concat(" AND DRIVING_LICENCE="+jd.getDrivingLicence());
//			}
//			if(jd.getCarowner()!=null)
//			{
//				query2.concat(" AND CAR_OWNER="+jd.getCarowner());
//			}
			
			
			st = connection.prepareStatement(query2);
			
			rs = st.executeQuery();
			
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
				candidateList.add(cd);
				json=new Gson().toJson(candidateList);
				
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
