package com.ashokit.utils;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ashokit.exceptionHandler.RegAppException;

import jakarta.mail.internet.MimeMessage;


@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendMail(String subject, String body, String to){
		
		boolean isMailSend=false;
	         MimeMessage mimeMessage=javaMailSender.createMimeMessage();
	         
	        try {  
	            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
	 
	          
	            mimeMessageHelper.setSubject(subject);
	            mimeMessageHelper.setText(body,true);
	            mimeMessageHelper.setTo(to);
	 
	            javaMailSender.send(mimeMessageHelper.getMimeMessage());
	            
	            isMailSend= true;
	        }
	 
	        catch (Exception e) {
	        	throw new RegAppException(e.getMessage());
	        }
	        return isMailSend;
	    }
}
