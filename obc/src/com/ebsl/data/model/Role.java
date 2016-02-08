package com.ebsl.data.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.ManyToOne;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = AUTO)
	protected long id;
	private String name;
	private String email;
	
	
	
	public Role() {
	}
	public Role(long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
