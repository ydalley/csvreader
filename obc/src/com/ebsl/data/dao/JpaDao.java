package com.ebsl.data.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebsl.utils.PageBean;

public abstract class JpaDao<E> implements Dao<E> {
	protected Class entityClass;

	@Autowired
	protected SessionFactory sessionFactory;

	public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public void persist(E entity) {
		sessionFactory.getCurrentSession().persist(entity);
	}
	
	@Override
	public void update(E entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	
	@Override
	public void remove(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public E findById(long id) {
		// TODO Auto-generated method stub
		E entity = (E) sessionFactory.getCurrentSession().get(entityClass, id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		String queryString = "from " + entityClass.getSimpleName();

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		List<E> list = query.list();
		return list;
	}
	
	public PageBean findAll( int max, int record) {
		String queryString = "from " + entityClass.getSimpleName();
		String cntString = "select count(e) from " + entityClass.getSimpleName() +" e";

		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setMaxResults(max);
		query.setFirstResult(record);
		List<E> list = query.list();
		query = sessionFactory.getCurrentSession().createQuery(
				cntString);
		long cnt = (long)query.uniqueResult();
		
		return preparePageBean(max, cnt, list);
	}

	@Override
	public List<E> findByProperty(String propertyName, String value) {
		String queryString = "from " + entityClass.getSimpleName()
				+ " as model where model." + propertyName + "= :value";
		List<E> list = null;
		try {
			Query queryObject = sessionFactory.getCurrentSession().createQuery(
					queryString);
			queryObject.setParameter("value", value);
			list = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<E> findByExample(E entity) {
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(entityClass)
				.add(Example.create(entity).ignoreCase()
						.enableLike(MatchMode.ANYWHERE));
		List<E> results = criteria.list();
		return results;
	}


	
	public PageBean findByExample(E entity, int max, int record) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass).add(Example.create(entity).
				enableLike(MatchMode.ANYWHERE).ignoreCase());

		Long cnt = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(record);
		criteria.setMaxResults(max);
		List results = criteria.list();
		return preparePageBean(max, cnt, results);
	}

	private PageBean preparePageBean(int page,long total ,List list){
		PageBean pb = new PageBean();
		pb.setData(list);
		pb.setRecordsFiltered(total);
		pb.setRecordsTotal(total);
		return pb;
	}
}