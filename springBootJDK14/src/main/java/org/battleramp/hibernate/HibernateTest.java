package org.battleramp.hibernate;

import java.util.List;

import org.api.wrapper.hibernate.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@SuppressWarnings("deprecation")
public class HibernateTest {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// UserDetails user = (UserDetails) session.get(UserDetails.class, 2);
		Query<UserDetails> query = session.createQuery("from USER_DETAILS");
		query.setCacheable(true);
		List<UserDetails> users = query.list();

		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

//		UserDetails user2 = (UserDetails) session.get(UserDetails.class, 2);
		query = session.createQuery("from USER_DETAILS");
		query.setCacheable(true);
		List<UserDetails> users2 = query.list();

		session.getTransaction().commit();
		session.close();

//		Query query = session.createQuery("from USER_DETAILS where USER_ID > :userID");
//		query.setFirstResult(2);
//		query.setMaxResults(2);
//		query.setInteger("userID", 1);

//		Query query = session.getNamedQuery("UserDetails.byID");
//		query.setInteger("userID", 1);
//		List<UserDetails> users = query.list();

//		Criteria criteria = session.createCriteria(UserDetails.class)
//				                    .setProjection(Projections.max("userId")); 

		// criteria.add(Restrictions.gt("userId", 2)).add(Restrictions.lt("userId", 5));
//		criteria.add(Restrictions.or(Restrictions.between("userId", 1, 5),Restrictions.like("userName", "%Test")));

//		List<UserDetails> users = criteria.list();

//		for (UserDetails user : users)
//			System.out.println(user.getUserName());

//		UserDetails user = new UserDetails();
//		user.setUserName("Vikas Yadav");
//		
//		user = null;
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		user = (UserDetails) session.get(UserDetails.class, 2);
//		user.setUserName("Hello Vikas");
//		session.getTransaction().commit();
//		session.close();
//
//		session = sessionFactory.openSession();
//		session.beginTransaction();		
//		user.setUserName("bye Vikas");
//		session.update(user);
//		session.getTransaction().commit();
//		session.close();

//		Address addr = new Address();
//		addr.setStreet("street name");
//		addr.setState("state name");
//		addr.setCity("city name");
//		addr.setPincode("pincode name");
//		
//
//		Address addr2 = new Address();
//		addr2.setStreet("street2 name");
//		addr2.setState("state2 name");
//		addr2.setCity("city2 name");
//		addr2.setPincode("pincode2 name");

//		UserDetails user = new UserDetails();
//		user.setUserName("Vikas Yadav");

//		Vehicle vehicle = new Vehicle();
//		vehicle.setVehicleName("CAR");
//
//		TwoWheeler bike = new TwoWheeler();
//		bike.setVehicleName("Bike");
//		bike.setSteeringHandle("Bike steering handle");
//		
//		FourWheller car = new FourWheller();
//		car.setVehicleName("Car");
//		car.setSteeringWheel("Car steering wheel");

//		user.getVehicleList().add(vehicle);
//		user.getVehicleList().add(vehicle2);

//		vehicle.getUserList().add(user);j
//		vehicle2.getUserList().add(user);

//		user.setHomeaddress(addr);
//		user.setOfficeaddress(addr);

//		user.getListOfAddresses().add(addr);
//		user.getListOfAddresses().add(addr2);

// ############### Insert Model Object ############

//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(vehicle);
//		session.save(bike);

//		for (int i = 0; i < 10; i++) {
//			UserDetails user = new UserDetails();
//			user.setUserName("Vikas Yadav " + i);
//			session.save(user);
//		}
//
//		session.getTransaction().commit();
//		session.close();

// ############# Extract Model Object ###############		

//		UserDetails user = null;
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		user = (UserDetails) session.get(UserDetails.class, 9);
//		System.out.println(user.getUserName());

// ############# Delete Model Object ###############		

//		UserDetails user = null;
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		user = (UserDetails) session.get(UserDetails.class, 6);
//		session.delete(user);
//		session.getTransaction().commit();

// ############# Update Model Object ###############		

//		UserDetails user = null;
//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		user = (UserDetails) session.get(UserDetails.class, 6);
//		user.setUserName("Hello Vikas");
//		session.update(user);
//		session.getTransaction().commit();
//		session.close();

	}
}
