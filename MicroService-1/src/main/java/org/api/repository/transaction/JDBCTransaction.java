package org.api.repository.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.api.wrapper.generic.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class JDBCTransaction {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private PlatformTransactionManager transactionManager;
	private final TransactionTemplate transactionTemplate;

	public JDBCTransaction(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		this.transactionTemplate.setTimeout(50);
		// Seralizable : prevents dirty reads , non-repetable and phantom reads
		this.transactionTemplate.setIsolationLevel(Isolation.SERIALIZABLE.ordinal());
	}

	private static final String fetchAllRecords = "SELECT * FROM USER";
	private static final String deleteUserRole = "DELETE FROM USER_ROLES WHERE ID = ? AND ACTIVE = ? ";
	private static final String insertUser = "INSERT INTO USER(ACTIVE,CREATE_ON,PASSWORD,USER_NAME) VALUES (?,?,?,?)";

	public List<UserWrapper> fetchAllUsers() {
//		return jdbcTemplate.queryForList(fetchAllRecords).stream().map(row -> {
//			UserWrapper user = new UserWrapper();
//			user.setId((int) row.get("id"));
//			user.setActive((String) row.get("active"));
//			user.setUserName((String) row.get("user_name"));
//			user.setCreatedOn((Timestamp) row.get("create_on"));
//			return user;
//		}).collect(Collectors.toList());

		// #using ROW MAPPER
		return jdbcTemplate.query(fetchAllRecords, new UserWrapperMapper());
	}

	public boolean deleteUser() {
		return jdbcTemplate.update(deleteUserRole, 1, "Y") > 0;
	}

	public int insertUser() {
		return jdbcTemplate.update(insertUser, "Y", Timestamp.from(Instant.now()), "pass", "UserName");
	}

	public int[][] insertBatchUser(int batchSize, boolean throwException) {
		List<UserWrapper> users = IntStream.range(1, 2000).boxed().map(x -> {
			UserWrapper user = new UserWrapper();
			user.setActive("Y");
			user.setUserName("batch" + System.nanoTime());
			user.setPassword("pass" + x);
			if (throwException && x == 500) {

			} else {
				user.setCreatedOn(Timestamp.from(Instant.now()));
			}
			return user;
		}).collect(Collectors.toList());

		return jdbcTemplate.batchUpdate(insertUser, users, batchSize,
				new ParameterizedPreparedStatementSetter<UserWrapper>() {
					@Override
					public void setValues(PreparedStatement ps, UserWrapper user) throws SQLException {
						ps.setString(1, user.getActive());
						ps.setTimestamp(2, user.getCreatedOn());
						ps.setString(3, user.getPassword());
						ps.setString(4, user.getUserName());
					}
				});
	}

	public String rollbackTransactionWithoutResult() {
		AtomicBoolean roolback = new AtomicBoolean(false);

		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// Object state1 = status.createSavepoint();
				try {
					insertBatchUser(300, true);
				} catch (Throwable ex) {
					// status.rollbackToSavepoint(state1);
					// or
					status.setRollbackOnly();
					// status.releaseSavepoint(state1);
					roolback.set(true);
				}
			}
		});

		return roolback.get() ? "Transaction Rollbacked" : "Transaction Complete";
	}

	public String rollbackTransactionPlatfromManager() {
		boolean roolback = false;
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		try {
			insertBatchUser(300, true);
		} catch (Throwable ex) {
			roolback = true;
			transactionManager.rollback(transactionStatus);
		}
		return roolback ? "Transaction Rollbacked" : "Transaction Complete";
	}

}
