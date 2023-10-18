package com.example.rooms.services.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rooms.model.Hotel;

@FeignClient("hotels")
public interface HotelFeingClient {
	
    @RequestMapping(method = RequestMethod.GET, value = "hotels-one/{id}", consumes = "application/json")
	public Hotel searchHotelRelatedHotel(@PathVariable long id);

}
