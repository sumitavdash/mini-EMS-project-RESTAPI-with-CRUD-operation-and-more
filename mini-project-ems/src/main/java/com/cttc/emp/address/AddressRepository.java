package com.cttc.emp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AddressRepository {

	@Autowired
	EntityManager entityManager;

	public boolean updateAddressIdInEmployee(String emp_id, int add_id) {

		String SQL = "update employee set address_id=" + add_id + " where emp_id=" + emp_id;
		int resultCount = entityManager.createNativeQuery(SQL).executeUpdate();
		boolean result = resultCount > 0 ? true : false;
		return result;
	}

}
