package org.api.controller.transactions;

import java.util.List;

import org.api.repository.transaction.JDBCTransaction;
import org.api.repository.transaction.JPATransaction;
import org.api.wrapper.generic.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-management")
public class JDBCTransactionManagementController {

	@Autowired(required = true)
	private JDBCTransaction jdbcTransaction;

	@Autowired(required = true)
	private JPATransaction jpaTransaction;

	@GetMapping("/fetch-transaction-jdbc")
	public List<UserWrapper> fetchTransactionJDBC() {
		return jdbcTransaction.fetchAllUsers();
	}

	@DeleteMapping("/delete-transaction-jdbc")
	public boolean deleteTransactionJDBC() {
		return jdbcTransaction.deleteUser();
	}

	@PostMapping("/insert-transaction-jdbc")
	public int insertTransactionJDBC() {
		return jdbcTransaction.insertUser();
	}

	@PostMapping("/batch-insert-transaction-jdbc/{batchSize}")
	public int[][] batchInsertTransactionJDBC(@PathVariable int batchSize) {
		// Dirty Reads are possible Here, if we make a select query during batch insert
		return jdbcTransaction.insertBatchUser(batchSize, false);
	}

	@GetMapping("/revert-transaction-jdbc")
	public String revertTransactionJDBC() {
		// Here if we don't set auto commit property by default all the records inserted
		// so far will not be reverted back
		return jdbcTransaction.rollbackTransactionWithoutResult();
		// Now we have applied the @Transactional Annotation which rollback all the
		// inserted records
	}

	@GetMapping("/rollback-transaction-jdbc")
	public String rollbackTransactionJDBC() {
		return jdbcTransaction.rollbackTransactionPlatfromManager();
	}

	
	@GetMapping("/fetch-transaction-jpa")
	public void fetchTransactionJTA() {
		jpaTransaction.fetchAllUsers();
	}

	
	
	@GetMapping("/revert-transaction-jtpa")
	public void revertTransactionJTA() {
		
	}
	
	
	

}
