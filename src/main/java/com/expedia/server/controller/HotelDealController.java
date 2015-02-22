/** 
 * @file	HotelDealController.java
 * @brief	HotelDealController.java is used to create from of home controllers
 *
 * @see	

 * @user k.salhotra
 * @Date Feb 22, 2015 
 */
package com.expedia.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.expedia.common.model.Response;
import com.expedia.server.model.DropDown;
import com.expedia.server.model.HotelDealData;
import com.expedia.server.service.HotelDealService;

@Controller
public class HotelDealController {
	
	@Autowired
	HotelDealService hotelDealService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = "/")
	public ModelAndView homeLogin() {
		List<HotelDealData> hotelDealsList = hotelDealService.getHotelDealsList(0);
		List<HotelDealData> recHotelList = hotelDealService.getRecommendedHotelList("Paris");
		List<DropDown> destinationList = hotelDealService.getDestinationList();
		ModelAndView model= new ModelAndView("index");
		model.addObject("hotelDealsList", hotelDealsList);
		model.addObject("recHotelList", recHotelList);
		model.addObject("destinationList", destinationList);
		return model;
	}
	
	
	@RequestMapping(value = {"/api/getRecHotels"}, method = RequestMethod.GET)
	@ResponseBody
	public Response<?> getRecommendedHotels(
			@RequestParam(value="checkInDate")String checkInDate,
			@RequestParam(value="checkOutDate")String checkOutDate,
			@RequestParam(value="destination")String destination) {
		List<HotelDealData> recList = hotelDealService.getRecommendedHotelList(checkInDate,checkOutDate,destination);
		Response<HotelDealData> recHotelListResponse = new Response<HotelDealData>(recList.size(),recList);
		return recHotelListResponse;
	}
	
}
