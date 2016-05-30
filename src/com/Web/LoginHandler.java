package com.Web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.beans.User;

public class LoginHandler {

	public ArrayList<User> getAllUsers(Connection connection) throws Exception {
		ArrayList<User> userList = new ArrayList<User>();
		try {
		
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM USER");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		User user = new User();
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		userList.add(user);
		}
		return userList;
		} catch (Exception e) {
		throw e;
		}
		}

}
