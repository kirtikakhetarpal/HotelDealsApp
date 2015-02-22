package com.expedia.server.dao;

import java.util.List;

import com.expedia.server.model.DropDown;
import com.expedia.server.model.HotelDealData;

public interface HotelDealDao {

	List<HotelDealData> getHotelDealsList(int count) ;

	List<HotelDealData> getRecommendedHotelList(String destinationName);

	List<DropDown> getDestinationList();

	List<HotelDealData> getRecommendedHotelList(String checkInDate,
			String checkOutDate, String destination);
}
