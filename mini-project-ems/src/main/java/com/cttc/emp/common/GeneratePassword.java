package com.cttc.emp.common;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GeneratePassword {
	public String generatePassword() {
		int length = 6;
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "0123456789";
		String values = Capital_chars + numbers;
		Random rndm_method = new Random();
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return new String(password);

	}
}
