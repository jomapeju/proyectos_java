package com.jomapeju.orm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "tareas")
@Data
public class Tarea implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@SequenceGenerator(name = "TASKS_ID_GENERATOR", sequenceName = "TASKS_SEQ", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASKS_ID_GENERATOR")
	private Long id;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "STATUS", nullable = true)
	private Integer status;
	
	@Column(name="creation_date")
	private Date creationDate;

}
