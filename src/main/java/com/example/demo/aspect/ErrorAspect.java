package com.example.demo.aspect;

import java.io.IOException;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {
	
	@AfterThrowing(value="execution(* *..*..*(..))" + "&& (bean(*Controller) || bean(*Service)) || bean(*Repository)",throwing="ex")
	public void throwingDataAccessException(DataAccessException ex) {
		System.out.println("=======================================================");
		System.out.println("DataAccessException発生。：" + ex);
		System.out.println("=======================================================");
	}

	@AfterThrowing(value="execution(* *..*..*(..))" + "&& (bean(*Controller) || bean(*Service)) || bean(*Repository)",throwing="ex")
	public void throwingFileIoException(IOException ex) {
		System.out.println("=======================================================");
		System.out.println("DataAccessException発生。：" + ex);
		System.out.println("=======================================================");
	}
}
