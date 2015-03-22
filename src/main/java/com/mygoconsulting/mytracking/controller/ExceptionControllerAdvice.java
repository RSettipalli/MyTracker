package com.mygoconsulting.mytracking.controller;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(HttpSessionRequiredException.class)
  public ModelAndView exception(HttpSessionRequiredException e) {
	  System.out.println("Exception is "+e);
	  e.printStackTrace();
	  return new ModelAndView("redirect:login.htm");
	  //return new ModelAndView("login","message", "User is not logged in, Please login");
  }
}
