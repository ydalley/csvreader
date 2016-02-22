package net.cafe.data.dao;

import java.util.List;

import net.cafe.data.model.Option;
import net.cafe.data.model.Profile;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


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
	
	
	
}
