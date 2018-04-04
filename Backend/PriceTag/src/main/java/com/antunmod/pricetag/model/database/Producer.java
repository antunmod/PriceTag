package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class references producers.
 */
@Entity
@Table(name = "producer")
public class Producer implements Serializable{

	private static final long serialVersionUID = -4967406054049231127L;
	
	@Id
	@Column(name = "producer_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short producerId;
	
	@Column(name = "producer_name")
	private String producerName;
}
