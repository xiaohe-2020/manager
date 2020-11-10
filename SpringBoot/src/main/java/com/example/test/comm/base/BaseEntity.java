package com.example.test.comm.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	@Column(insertable = false, updatable = false)
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


}
