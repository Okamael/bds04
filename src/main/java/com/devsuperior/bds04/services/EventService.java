package com.devsuperior.bds04.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	

	@Transactional
	public @Valid EventDTO insert(@Valid EventDTO dto) {
		Event newEvent = new Event();
		geraEntity(newEvent,dto);
		newEvent = repository.save(newEvent);
		return new EventDTO(newEvent);
	}

	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable) {

		Page<Event> list = repository.findAll(pageable);
		return list.map(x -> new EventDTO(x));
	}

	private void geraEntity(Event newEvent, EventDTO dto) {
		newEvent.setName(dto.getName());
		newEvent.setDate(dto.getDate());
		newEvent.setUrl(dto.getUrl());
		newEvent.setCity(cityRepository.getOne(dto.getCityId()));
	}

}
