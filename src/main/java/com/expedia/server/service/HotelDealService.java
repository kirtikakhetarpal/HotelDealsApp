/**
 * 
 */
package com.expedia.server.service;

import java.util.List;

import com.expedia.server.model.DropDown;
import com.expedia.server.model.HotelDealData;



/**
 * @author k.salhotra
 *
 */
public interface HotelDealService {

	List<HotelDealData> getHotelDealsList(int count);

	List<HotelDealData> getRecommendedHotelList(String destinationName);

	List<DropDown> getDestinationList();

	List<HotelDealData> getRecommendedHotelList(String checkInDate, String checkOutDate, String destination);


	

}
