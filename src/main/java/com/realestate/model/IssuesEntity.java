package com.realestate.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import javax.persistence.Table;


@Entity
@Table(name="issues")

public class IssuesEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="resolution_status")
	private String resolution_status;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="created_date")
	private GregorianCalendar created_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user", referencedColumnName = "user_id" )
	private UserEntity user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="property", referencedColumnName = "property_id")
	private PropertyEntity property;
	
	


	
	
	
	
	

}
