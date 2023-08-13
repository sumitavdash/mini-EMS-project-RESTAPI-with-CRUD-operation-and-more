package com.cttc.emp.registration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cttc.emp.address.AddressDao;
import com.cttc.emp.common.GeneratePassword;
import com.cttc.emp.common.SendMailService;
import com.cttc.emp.registration.repository.EmployeeDAO;
import com.cttc.emp.registration.repository.EmployeeRepository;
import com.cttc.enitty.Address;
import com.cttc.enitty.Employee;

import jakarta.persistence.EntityManager;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	GeneratePassword generatePassword;

	@Autowired
	SendMailService sendMailService;

	@Autowired
	AddressDao addressDao;

	@Override
	public ResponseEntity<Object> saveEmp(Employee employee) {
		Map<String, Object> response = new LinkedHashMap<>();
		// boolean isMailExist =
		// employeeRepository.checkMailAndMobilefromDb(employee.getMailId());
		List<Employee> emp = employeeDAO.findByMailId(employee.getMailId());
		if (emp.size() > 0) {
			response.put("status", "ERROR");
			response.put("message", "Data already Exist");
		} else {
			employee.setPassword(generatePassword.generatePassword());
			employeeDAO.save(employee);
			sendMailService.sendMail(employee, "save");
			response.put("status", "OK");
			response.put("message", "Data Inserted Successfully., Check Your Mail For Password.");

		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> loginEmp(Employee employee) {
		Map<String, Object> response = new LinkedHashMap<>();
		Employee loginEmpData = employeeRepository.loginEmpData(employee);
		if (loginEmpData == null) {
			response.put("status", "ERROR");
			response.put("message", "Invalid User Name/ Password");
		} else {
			response.put("status", "OK");
			response.put("data", loginEmpData);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findAllEmp() {
		Map<String, Object> response = new LinkedHashMap<>();
		List<Employee> listEmpData = employeeDAO.findAll();
		if (listEmpData.isEmpty()) {
			response.put("status", "ERROR");
			response.put("message", "No Data Found!");
		} else {
			response.put("status", "OK");
			response.put("count", listEmpData.size());
			response.put("data", listEmpData);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> forgotPasswordEmp(String mail) {
		Map<String, Object> response = new LinkedHashMap<>();
		List<Employee> emp = employeeDAO.findByMailId(mail);
		if (emp.size() == 0) {
			response.put("status", "ERROR");
			response.put("message", "Mail Id Does Not Exist!");
		} else {

			sendMailService.sendMail(emp.get(0), "forgot");
			response.put("status", "OK");
			response.put("message",
					"Password send Successfully to " + emp.get(0).getMailId() + ", Check Your Mail For Password.");

		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> changePassword(Employee employee, String newPwd) {
		Map<String, Object> response = new LinkedHashMap<>();
		Employee loginEmpData = employeeRepository.loginEmpData(employee);
		if (loginEmpData == null) {
			response.put("status", "ERROR");
			response.put("message", "Invalid User Name/ Password, You can Reset the password.");
		} else {
			String msg = employeeRepository.changePassword(loginEmpData.getID(), newPwd);
			if (msg.equals("success")) {
				response.put("status", "OK");
				response.put("message", "Password Change Successfully.");
			} else {
				response.put("status", "ERROR");
				response.put("message", "Something wrong, Try again!");
			}
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findEmpWithAddress(int emp_id) {
		Map<String, Object> response = new LinkedHashMap<>();
		Optional<Employee> employee = employeeDAO.findById(emp_id);
		Optional<Address> address = addressDao.findById(employee.get().getAddressid());
		response.put("status", "OK");
		response.put("emp_id", emp_id);
		response.put("employee", employee);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
