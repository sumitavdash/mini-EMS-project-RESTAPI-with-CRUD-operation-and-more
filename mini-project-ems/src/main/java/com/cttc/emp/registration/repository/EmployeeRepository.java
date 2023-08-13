package com.cttc.emp.registration.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cttc.enitty.Employee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager entityManager;

	@Autowired
	EmployeeDAO employeeDAO;

	public boolean checkMailAndMobilefromDb(String mail) {
		String SQL = "select * from employee where mail_id='" + mail + "'";
		List<Employee> emp = entityManager.createNativeQuery(SQL, Employee.class).getResultList();
		if (emp.isEmpty())
			return false;
		else
			return true;
	}

	public Employee loginEmpData(Employee employee) {
		return employeeDAO.findByMailIdAndPassword(employee.getMailId(), employee.getPassword());
	}

	public String changePassword(int id, String newPwd) {
		String SQL = "update employee set password='" + newPwd + "' where emp_id=" + id;
		int count = entityManager.createNativeQuery(SQL).executeUpdate();
		if (count > 0)
			return "success";
		else
			return "";
	}
}
