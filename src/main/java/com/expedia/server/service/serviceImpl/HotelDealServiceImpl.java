/**
 * 
 */
package com.expedia.server.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedia.server.dao.HotelDealDao;
import com.expedia.server.model.DropDown;
import com.expedia.server.model.HotelDealData;
import com.expedia.server.service.HotelDealService;

/**
 * @author k.salhotra
 *
 */
@Service
public class HotelDealServiceImpl implements HotelDealService {

	@Autowired
	HotelDealDao hotelDealDao;
	
	public List<HotelDealData> getHotelDealsList(int count) {
		return hotelDealDao.getHotelDealsList(count);
	}

	public List<HotelDealData> getRecommendedHotelList(String destinationName) {
		return hotelDealDao.getRecommendedHotelList(destinationName);
	}

	public List<DropDown> getDestinationList() {
		return hotelDealDao.getDestinationList();
	}

	public List<HotelDealData> getRecommendedHotelList(String checkInDate, String checkOutDate, String destination) {
		return hotelDealDao.getRecommendedHotelList(checkInDate,checkOutDate,destination);
	}

	
	
}
