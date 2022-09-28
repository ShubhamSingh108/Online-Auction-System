
  package com.app.auction.services;
  
  import java.io.IOException;
  
  public interface EmailService { 
	  public void sendEmail(String message, String subject, String to, String from);
  
  }
 