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
public class Producer implements Serializable {

	private static final long serialVersionUID = -4967406054049231127L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "name")
	private String name;

	public Producer() {
	}

	public Producer(String name) {
		super();
		this.name = name;
	}

	public Short getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
