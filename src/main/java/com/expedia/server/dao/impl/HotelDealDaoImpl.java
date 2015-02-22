/** 
 * @file	HotelDealDaoImpl.java
 * @brief	HotelDealDaoImpl.java is used to create data access objects for hotel deals 
 *
 * @see	

 * @user k.salhotra
 * @Date Feb 22 2015
 * 
 */
package com.expedia.server.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hsqldb.lib.HashSet;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.expedia.server.dao.HotelDealDao;
import com.expedia.server.model.DropDown;
import com.expedia.server.model.HotelDealData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
/**
 * @author k.salhotra
 * 
 */
@Repository
public class HotelDealDaoImpl implements HotelDealDao {

	
	Map<String,List<HotelDealData>> destinationHotelMap = new HashMap<String, List<HotelDealData>>();
	
	List<DropDown> destinationList = new ArrayList<DropDown>();
	
	List<DropDown> offerOfTheDayList = new ArrayList<DropDown>();
	
	HashSet destinationSet = new HashSet();
	
	public Map<String, List<HotelDealData>> getDestinationHotelMap() {
		
		if(destinationHotelMap == null)
			getHotelDealsList(0);
		return destinationHotelMap;
	}



	public void setDestinationHotelMap(
			Map<String, List<HotelDealData>> destinationHotelMap) {
		this.destinationHotelMap = destinationHotelMap;
	}



	@Cacheable("hotelDeals")
	public List<HotelDealData> getHotelDealsList(int count){

		StringBuffer output = new StringBuffer();
		
		try {
			URL url = new URL("http://deals.expedia.com/beta/deals/hotels.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			
			String data ;
			while ((data = br.readLine()) != null) {
				output.append(data);
			}
            count++;
			conn.disconnect();
			
			if((output==null || "".equals(output))  && count < 5){
				getHotelDealsList(count);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	
		TypeToken<List<HotelDealData>> token = new TypeToken<List<HotelDealData>>(){};
		List<HotelDealData> hotelDealsList = new Gson().fromJson(output.toString(), token.getType());	
		if(hotelDealsList != null)
		populateDestinationHotelMap(hotelDealsList,destinationHotelMap);
		return hotelDealsList;
	}

	

	private void populateDestinationHotelMap(
			List<HotelDealData> hotelDealsList,
			Map<String, List<HotelDealData>> destinationHotelMap) {
	
		Iterator<HotelDealData> dealIterator = hotelDealsList.iterator();
		HotelDealData hotelDeal = null;
		List<HotelDealData> recHotel = null;
		int index = 0;
		while(dealIterator.hasNext())
		{
			
			hotelDeal = dealIterator.next();
			if(destinationSet.contains(hotelDeal.getDestination()) == false)
			{
				destinationSet.add(hotelDeal.getDestination());
				destinationList.add(new DropDown(index++,hotelDeal.getDestination()));
			}
			
			String destination =hotelDeal.getDestination();
			if(destinationHotelMap.get(destination) == null)
			{
				recHotel = new ArrayList<HotelDealData>();
			}
			else
			{
				recHotel = destinationHotelMap.get(destination);
			}
			
			recHotel.add(hotelDeal);
			destinationHotelMap.put(destination,recHotel);
		}
		
		
	}



	public List<HotelDealData> getRecommendedHotelList(String destinationName) {
		List<HotelDealData> recHotelList = destinationHotelMap.get(destinationName);
		Collections.sort(recHotelList, new Comparator<HotelDealData>() {
			public int compare(HotelDealData o1, HotelDealData o2) {
				return (int) (o1.getStarRating()-o2.getStarRating());
			}	
			
		});
		Iterator<String> iterator = destinationHotelMap.keySet().iterator();
		
		while(iterator.hasNext())
		{
			if(recHotelList.size() < 10)
			{
				String destination = iterator.next();
				if(false == recHotelList.contains(destinationHotelMap.get(destination).get(0)))
						{
					recHotelList.add(destinationHotelMap.get(destination).get(0));
						}
			}
			else
			{
				break;
			}
		}
		return recHotelList;
	}



	public List<DropDown> getDestinationList() {
		
		if(destinationList.isEmpty())
			getDestinationHotelMap();
		return destinationList;
	}



	public List<HotelDealData> getRecommendedHotelList(String checkInDate,
			String checkOutDate, String destination) {
		
		List<HotelDealData> hotelList = destinationHotelMap.get(destination);
		List<HotelDealData> result = new ArrayList<HotelDealData>();
		
		if(hotelList!=null){
		Iterator<HotelDealData> iterator = hotelList.iterator();
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	

		try {
			Date inDate = formatter.parse(checkInDate);
			Date outDate = formatter.parse(checkOutDate);
			HotelDealData hotelDealData = null;
			while(iterator.hasNext())
			{
				hotelDealData = iterator.next();
				Date hotelInDate = formatter1.parse(hotelDealData.getCheckInDate());
				Date hotelOutDate = formatter1.parse(hotelDealData.getCheckOutDate());
				if((hotelInDate.equals(inDate)||hotelInDate.before(inDate)) && (hotelOutDate.equals(outDate)||hotelOutDate.after(outDate)))
				{
					if(!result.contains(hotelDealData))
						result.add(hotelDealData);
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		return result;
	}

	
	

}
