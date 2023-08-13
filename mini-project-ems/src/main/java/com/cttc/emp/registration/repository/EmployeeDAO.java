package com.cttc.emp.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cttc.enitty.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{

	List<Employee> findByMailId(String mailId);
	Employee findByMailIdAndPassword(String mailId,String password);

}
