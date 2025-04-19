package com.restaurent.service.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.restaurent.dto.CityDto;
import com.restaurent.dto.request.CityRequest;

public interface CityService {

	List<CityDto> getAllCities();

	CityDto getCityById(long id);

	CityDto createCity(CityRequest city);

	@Transactional
	CityDto updateCity(long id, CityRequest city);

	void deleteCity(long id);



}
