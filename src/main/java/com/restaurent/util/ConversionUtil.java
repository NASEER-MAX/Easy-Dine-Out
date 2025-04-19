package com.restaurent.util;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionUtil {
	private static DozerBeanMapper dozerMapper;

	@Bean
	DozerBeanMapper getDozerMapper() {
		dozerMapper = new DozerBeanMapper();
		return dozerMapper;
	}

//	public static <T> T convert(Object source, Class<T> destinationClass) {
//		T destination = dozerMapper.map(source, destinationClass);
//		return destination;
//	}
}
