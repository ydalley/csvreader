package net.cafe.data.dao;


import net.cafe.data.model.Profile;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


@Repository
public class ProfileDao extends JpaDao<Profile> {

	public Profile loadProfile(long id){
		Profile fullprofile = findById(id);
		Hibernate.initialize(fullprofile.getOptions());
		return fullprofile;
	}
}
