package com.cttc.enitty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private int ID;

	@Column(name = "first_name", length = 100)
	private String fname;

	@Column(name = "last_name", length = 100)
	private String lname;

	@Column(name = "mail_id", length = 100, unique = true)
	private String mailId;

	@Column(name = "mobile", length = 100, unique = true)
	private String mobile;

	@JsonIgnore
	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "dob")
	private LocalDate dateOfBirth;

	@Column(name="address_id", insertable=false, updatable=false)
	private int addressid;
	
	@JsonFormat(pattern = "dd-MM-yyy hh:mm:ss a")
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	Address address;
}
