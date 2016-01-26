package com.ebsl.data.dao;


import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebsl.data.model.Code;
import com.ebsl.data.model.User;
import com.ebsl.utils.PageBean;

@Repository("JpaDao")
public class UserDao extends JpaDao<User>{

	public List<User> findByProperty(String propertyName, String value) {
		String queryString = "from User as model where model." + propertyName + "= ?";
		List<User> list = null;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			list = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
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
	
	public PageBean findByFullText(String pattern, int max, int record) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(User.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy()
				.onFields("firstName", "lastName", "email","loginId","phone").matching(pattern)
				.createQuery();
		

		// wrap Lucene query in a javax.persistence.Query
		FullTextQuery fullTextQuery = fSession.createFullTextQuery(luceneQuery,
				User.class);
		long size = fullTextQuery.getResultSize();

		fullTextQuery.setMaxResults(max);
		fullTextQuery.setFirstResult(record);
		List list = fullTextQuery.list();
		
		PageBean pb = new PageBean();
		pb.setData(list);
		pb.setRecordsFiltered(size);
		pb.setRecordsTotal(size);

		return pb;
	}
	
	
}
