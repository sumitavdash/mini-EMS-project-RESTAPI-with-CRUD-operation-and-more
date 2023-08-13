package com.cttc.emp.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cttc.enitty.Employee;

@Service
public class SendMailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(Employee employee,String m) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(employee.getMailId());
		msg.setSubject("EMS Project");
		switch (m) {
		case "save": {
			msg.setText("Hello " + employee.getFname() + ",\n Your registration with EMS Successfull!"+"\n Your Password is :"+employee.getPassword());
			break;
		}
		case "forgot":{
			msg.setText("Hello " + employee.getFname() + ","+"\n Your Password is :"+employee.getPassword());
			break;
		}
		default:
			System.out.println("Invalid Choice");
			break;
		}
		
		javaMailSender.send(msg);
		System.out.println("Mail Send Success to "+employee.getMailId());
	}
}
