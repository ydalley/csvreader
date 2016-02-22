package net.cafe.data.dao;


import java.util.List;

import net.cafe.data.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("JpaDao")
public class UserDao extends JpaDao<User>{

	
	public User getActiveUserByName(String name) {
		String queryString = "from User as model where model.loginId = ? and model.status='E' ";
		User u = null ;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, name);
			u = (User)queryObject.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public User getUserByName(String name) {
		String queryString = "from User as model where model.loginId = ?";
		User u = null ;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, name);
			u = (User)queryObject.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	
}
