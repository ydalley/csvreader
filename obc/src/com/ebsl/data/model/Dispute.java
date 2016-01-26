package com.ebsl.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.apache.struts2.json.annotations.JSON;
@Entity
public class Dispute {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	private String comments;
	private String status;
	private Date Date;
	private String loggedBy;
	private String approvedBy;
	
	
	@Version
    protected int version;
	@JSON(name="DT_RowId")
	public long getId() {
		return id;
	}
}
