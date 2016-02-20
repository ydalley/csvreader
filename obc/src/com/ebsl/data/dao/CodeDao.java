package com.ebsl.data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.ebsl.data.model.Code;
import com.ebsl.utils.PageBean;

@Repository
public class CodeDao extends JpaDao<Code> {

	
	public PageBean findByFullText(String pattern, int max, int record) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Code.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy()
				.onFields("code", "type", "description").matching(pattern)
				.createQuery();

		// wrap Lucene query in a javax.persistence.Query
		FullTextQuery fullTextQuery = fSession.createFullTextQuery(luceneQuery,
				Code.class);
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
