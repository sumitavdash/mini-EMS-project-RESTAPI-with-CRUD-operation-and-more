package com.cttc.emp.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cttc.enitty.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saveEmp")
	public ResponseEntity<Object> saveEmp(@RequestBody Employee employee) {
		return employeeService.saveEmp(employee);
	}
	
	@PostMapping("/loginEmp")
	public ResponseEntity<Object> loginEmp(@RequestBody Employee employee) {
		return employeeService.loginEmp(employee);
	}
	
	@GetMapping("/findAllEmp")
	public ResponseEntity<Object> findAllEmp(){
		return employeeService.findAllEmp();
	}
	
	@GetMapping("/forgotPasswordEmp/{mail}")
	public ResponseEntity<Object> forgotPasswordEmp(@PathVariable String mail){
		return employeeService.forgotPasswordEmp(mail);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<Object> changePassword(@RequestBody Employee employee, @RequestParam String newPwd){
		return employeeService.changePassword(employee,newPwd);
	}
	
	@GetMapping("/findEmpWithAddress/{emp_id}")
	public ResponseEntity<Object> findEmpWithAddress(@PathVariable int emp_id){
		return employeeService.findEmpWithAddress(emp_id);
	}
}
