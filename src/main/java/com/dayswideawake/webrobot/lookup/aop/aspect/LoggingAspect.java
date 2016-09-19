package com.dayswideawake.webrobot.lookup.aop.aspect;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	@Around("execution(* *(..)) && @annotation(com.dayswideawake.webrobot.lookup.aop.annotation.Loggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = point.proceed();
		String where = point.getSignature().getDeclaringType().getName() + "." + point.getSignature().getName();
		String args = Arrays.asList(point.getArgs())
				.stream()
				.map(arg -> arg.toString())
				.collect(Collectors.joining(", "));
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info(String.format( ANSI_PURPLE + "%s(" + ANSI_GREEN + "%s" + ANSI_PURPLE + "): " + ANSI_CYAN + "%s (%s msec)" + ANSI_RESET, where, args, result, elapsedTime));
		return result;
	}
	
}
