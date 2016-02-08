package com.ebsl.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.ebsl.data.model.Option;
import com.ebsl.data.model.Profile;
import com.ebsl.utils.PageBean;

@Repository
public class OptionDao extends JpaDao<Option> {

	public List<Option> findOtherOptions(Profile f) {
		
		String query  = "select op.id from Profile p  join p.options op  where p.id = :profid";
		List<Option> list = null;
		List<Long> foptions = null;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(query);
			queryObject.setParameter("profid",f.getId());
			foptions = queryObject.list();
			if(foptions.isEmpty()){
				query  = "select option from Option option";
				queryObject = sessionFactory.getCurrentSession().createQuery(query);
			}else{
				query  = "select option from Option option where option.id not in (:oplist)";
				queryObject = sessionFactory.getCurrentSession().createQuery(query);
				queryObject.setParameterList("oplist",foptions);
			}
			
			list = queryObject.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List getOptionDesc(String[] options) {
		String query  = "select op.description from Option op  where op.name in (:opstr)";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(query);
		queryObject.setParameterList("opstr",options);
		return queryObject.list();
	}
	
	public PageBean findByFullText(String pattern, int max, int record) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Option.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy()
				.onFields("name", "description").matching(pattern)
				.createQuery();
		

		// wrap Lucene query in a javax.persistence.Query
		FullTextQuery fullTextQuery = fSession.createFullTextQuery(luceneQuery,
				Option.class);
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
