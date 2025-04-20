package com.restaurent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.CityDto;
import com.restaurent.dto.request.CityRequest;
import com.restaurent.service.interfaces.CityService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/cities")
@AllArgsConstructor
@RestController
public class CityController {

	CityService cityService;

	@GetMapping
	ResponseEntity<List<CityDto>> getAllCities() {
		return ResponseEntity.ok(cityService.getAllCities());
	}

	@GetMapping("/{id}")
	ResponseEntity<CityDto> getCityById(@PathVariable("id") long id) {
		return ResponseEntity.ok(cityService.getCityById(id));
	}

	@PostMapping
	ResponseEntity<CityDto> createCity(@Validated @RequestBody CityRequest city) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(city));
	}

	@PutMapping("/{id}")
	ResponseEntity<CityDto> updateCity(@PathVariable("id") long id, @Validated @RequestBody CityRequest city) {
		return ResponseEntity.ok(cityService.updateCity(id, city));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteCity(@PathVariable("id") long id) {
		cityService.deleteCity(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
