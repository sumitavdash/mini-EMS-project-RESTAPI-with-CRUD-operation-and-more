package com.cttc.emp.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cttc.enitty.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {

}
