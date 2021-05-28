package org.api.repository.transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.api.wrapper.generic.UserWrapper;
import org.springframework.jdbc.core.RowMapper;

public class UserWrapperMapper implements RowMapper<UserWrapper> {

	@Override
	public UserWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserWrapper user = new UserWrapper();
		user.setId(rs.getInt("id"));
		user.setActive(rs.getString("active"));
		user.setUserName(rs.getString("user_name"));
		user.setCreatedOn(rs.getTimestamp("create_on"));
		return user;
	}

}
