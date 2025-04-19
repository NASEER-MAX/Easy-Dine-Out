package com.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.CityAdapter;
import com.restaurent.dto.CityDto;
import com.restaurent.dto.request.CityRequest;
import com.restaurent.entity.City;
import com.restaurent.repository.CityRepository;
import com.restaurent.service.interfaces.CityService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	CityRepository cityRepository;
	CityAdapter cityAdapter;

	@Override
	public List<CityDto> getAllCities() {
		return cityAdapter.toDto(cityRepository.findAll());
	}

	@Override
	public CityDto getCityById(long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found with ID: "
				+ id));
		return cityAdapter.toDto(city);
	}

	@Override
	public CityDto createCity(CityRequest cityRequest) {
		City city = new City();
		city.setCity(cityRequest.getCity());

		cityRepository.save(city);
		return cityAdapter.toDto(city);
	}

	@Transactional
	@Override
	public CityDto updateCity(long id, CityRequest cityRequest) {

		City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found with ID: "
				+ id));
		city.setCity(cityRequest.getCity());

		cityRepository.save(city);
		return cityAdapter.toDto(city);

	}

	@Transactional
	@Override
	public void deleteCity(long id) {
		if (!cityRepository.existsById(id)) {
			throw new EntityNotFoundException("City not found with ID: " + id);
		}
		cityRepository.deleteById(id);

	}
}
