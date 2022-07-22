package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(city -> new CityDTO(city) ).collect(Collectors.toList());
	}

	public @Valid CityDTO insert(@Valid CityDTO dto) {
		
		City newCity = new City();
		newCity.setName(dto.getName());
		newCity = repository.save(newCity);
		return new CityDTO(newCity);
	}

}
