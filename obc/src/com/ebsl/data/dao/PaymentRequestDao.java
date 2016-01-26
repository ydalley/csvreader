package com.ebsl.data.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.ebsl.data.model.PaymentRequest;
import com.ebsl.utils.PageBean;

@Repository
public class PaymentRequestDao extends JpaDao<PaymentRequest>{

	@Override
	public List<PaymentRequest> findByProperty(String propertyName, String value) {
		String queryString = "from PaymentRequest as model where model." + propertyName + "= ?";
		List<PaymentRequest> list = null;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			list = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public PageBean findByFullText(String pattern, int max, int record) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(PaymentRequest.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy()
				.onFields("merchantAccountName").matching(pattern)
				.createQuery();
		

		// wrap Lucene query in a javax.persistence.Query
		FullTextQuery fullTextQuery = fSession.createFullTextQuery(luceneQuery,
				PaymentRequest.class);
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
