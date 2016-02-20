package com.ebsl.data.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.ebsl.data.model.PaymentRequest;
import com.ebsl.utils.PageBean;

@Repository
public class PaymentRequestDao extends JpaDao<PaymentRequest> {

	@Override
	public List<PaymentRequest> findByProperty(String propertyName, String value) {
		String queryString = "from PaymentRequest as model where model."
				+ propertyName + "= ?";
		List<PaymentRequest> list = null;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(
					queryString);
			queryObject.setParameter(0, value);
			list = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PageBean findByExample(PaymentRequest entity, int record, int max) {
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(PaymentRequest.class)
				.add(Example.create(entity).ignoreCase()
						.enableLike(MatchMode.ANYWHERE));

		ScrollableResults results = criteria.scroll();
		results.last();
		Long cnt = results.getRowNumber() + 1L;
		results.close();

		criteria.setFirstResult(record);
		criteria.setMaxResults(max);
		List list = criteria.list();

		PageBean pb = new PageBean();
		pb.setData(list);
		pb.setRecordsFiltered(cnt);
		pb.setRecordsTotal(cnt);

		return pb;
	}

	public PageBean findByExample(PaymentRequest entity, Double paymentUpper,
			Date start, Date end, int record, int max) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(PaymentRequest.class)
				.add(Restrictions.ge("paymentDate", start))
				.add(Restrictions.lt("paymentDate", end));

		if (entity.getPaymentAmount() == null) {
			entity.setPaymentAmount(paymentUpper);
		}

		if (entity.getPaymentAmount() != null) {

			Double low = Math.ceil(entity.getPaymentAmount());
			Double high = null;
			if (paymentUpper == null) {
				high = Math.ceil(entity.getPaymentAmount() + 1);
			} else {
				high = Math.ceil(Math.max(low, paymentUpper) + 1);
				low = Math.ceil(Math.min(low, paymentUpper));
			}
			
			criteria
			.add(Restrictions.ge("paymentAmount", low))
			.add(Restrictions.lt("paymentAmount", high));
			entity.setPaymentAmount(null);
		}

		criteria.add(Example.create(entity).ignoreCase()
				.enableLike(MatchMode.ANYWHERE));

		// Long cnt = (Long) criteria.setProjection(Projections.rowCount());

		ScrollableResults results = criteria.scroll();
		results.last();
		Long cnt = results.getRowNumber() + 1L;
		results.close();

		criteria.setFirstResult(record);
		criteria.setMaxResults(max);
		List list = criteria.list();

		PageBean pb = new PageBean();
		pb.setData(list);
		pb.setRecordsFiltered(cnt);
		pb.setRecordsTotal(cnt);

		return pb;
	}

	public PageBean findByFullText(String pattern, int max, int record) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fSession.getSearchFactory()
				.buildQueryBuilder().forEntity(PaymentRequest.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword()
				.fuzzy().onFields("merchantAccountName").matching(pattern)
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

	private PageBean preparePageBean(int page, long total, List list) {
		PageBean pb = new PageBean();
		pb.setData(list);
		pb.setRecordsFiltered(total);
		pb.setRecordsTotal(total);
		return pb;
	}

}
