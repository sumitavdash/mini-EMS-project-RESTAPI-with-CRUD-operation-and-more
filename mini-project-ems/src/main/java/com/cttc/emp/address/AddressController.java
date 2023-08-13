package com.cttc.emp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cttc.enitty.Address;

@RestController
@RequestMapping("/emp")
public class AddressController {
	@Autowired
	AddressService addressService;
	@PostMapping("/saveAddress/{emp_id}")
	public ResponseEntity<Object> saveAddress(@RequestBody Address address, @PathVariable("emp_id") String emp_id) {
		return addressService.saveAddress(address,emp_id);
	}
}
