package com.ebsl.actions;

import com.ebsl.data.model.User;

public interface UserAware {

	void setCurrentUser(User user);
	
}
