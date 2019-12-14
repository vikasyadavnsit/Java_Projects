package org.battleramp.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.battleramp.wrapper.TestWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDataRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public List<TestWrapper> getAllData() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		TestWrapper wrapper = new TestWrapper("vickytauruss@gmail.com");
		session.save(wrapper);
		System.out.println("Autogenerate ID : " + wrapper.getId());
		session.beginTransaction().commit();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TestWrapper> cq = cb.createQuery(TestWrapper.class);
		Root<TestWrapper> rootEntry = cq.from(TestWrapper.class);
		CriteriaQuery<TestWrapper> all = cq.select(rootEntry);

		TypedQuery<TestWrapper> allQuery = session.createQuery(all);
		return allQuery.getResultList();
	}

}
