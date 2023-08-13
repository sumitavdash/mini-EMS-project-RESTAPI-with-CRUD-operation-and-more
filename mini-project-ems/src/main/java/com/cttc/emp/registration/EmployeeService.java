package com.cttc.emp.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cttc.enitty.Employee;

@Service
public interface EmployeeService {

	ResponseEntity<Object> saveEmp(Employee employee);

	ResponseEntity<Object> loginEmp(Employee employee);

	ResponseEntity<Object> findAllEmp();

	ResponseEntity<Object> forgotPasswordEmp(String mail);

	ResponseEntity<Object> changePassword(Employee employee, String newPwd);

	ResponseEntity<Object> findEmpWithAddress(int emp_id);

}
