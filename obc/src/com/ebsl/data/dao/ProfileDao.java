package com.ebsl.data.dao;


import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.ebsl.data.model.Profile;

@Repository
public class ProfileDao extends JpaDao<Profile> {

	public Profile loadProfile(long id){
		Profile fullprofile = findById(id);
		Hibernate.initialize(fullprofile.getOptions());
		return fullprofile;
	}
}
