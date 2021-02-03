package it.gbs.weather.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.gbs.weather.model.Coordinates;
import it.gbs.weather.model.Daily;
import it.gbs.weather.model.DailyFormatted;
import it.gbs.weather.model.GetCurrent;
import it.gbs.weather.model.GetDaily;
import it.gbs.weather.model.GetDailyFormatted;
import it.gbs.weather.service.OpenWeatherService;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);
	
	@Value( "${openweather.get.current.url}" )
	private String currentUrl;
	
	@Value( "${openweather.get.week.url}" )
	private String weekUrl;
	
	@Value( "${openweather.key}" )
	private String appId;

	/**
	 * Retrieve weather forecast of next two days
	 */
	@Override
	public GetDaily getNextTwoDayWeather(String cityName) throws Exception{
		Coordinates coordinates = getCoordinates(cityName);
		RestTemplate restTemplate = new RestTemplate();
		GetDaily daily = new GetDaily();
		try {
			logger.debug("send getNextTwoDays request");
			
			//retrieve week forecast
			daily = restTemplate
					  .getForObject(weekUrl + "?lat=" + coordinates.getLat() + 
							  "&lon=" + coordinates.getLon() + 
							  "&exclude=minutely,hourly,current,alerts" + 
							  "&units=metric" + 
							  "&appid=" + appId, GetDaily.class);
			daily.setCityName(cityName);
			
			//leave only first two days forecast
			if(daily.getDaily().size() >= 7) {
				for(int i = 7 ; i > 2 ; i--) {
					daily.getDaily().remove(i);
				}
			}
		}catch(Exception e) {
			throw e;
		}
		
		return daily;
	}

	/**
	 * Retrieve coordinates from cityName
	 */
	@Override
	public Coordinates getCoordinates(String cityName) throws Exception{
		
		if(cityName == null) {
			throw new Exception("Given cityName is null");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		GetCurrent getCurrent = new GetCurrent();
		
		try {
			/*
			 * Send getCurrentWeather request for getting coordinates of cityName.
			 * Retrieved coordinates will be used to perform one call request to get next two days 
			 * weather forecast.
			 */
			logger.debug("send getCurrentWeather request");
			getCurrent = restTemplate
					  .getForObject(currentUrl + "?q=" + cityName + "&appid=" + appId, GetCurrent.class);
		}catch(Exception e) {
			throw e;
		}
		
		logger.debug("Coordinates of " + cityName + ": " + getCurrent.getCoord());
		
		return getCurrent.getCoord();
	}

	/**
	 * Retrieve weather forecast of next two days formatted
	 */
	@Override
	public GetDailyFormatted getNextTwoDayWeatherFormatted(String cityName) throws Exception {
		GetDaily daylies = getNextTwoDayWeather(cityName);
		return convertToGetDailyToReturn(daylies);
	}
	
	/**
	 * convert GetDaily object to GetDailyFormatted
	 */
	private GetDailyFormatted convertToGetDailyToReturn(GetDaily dailies) {
		GetDailyFormatted getDailyToReturn = new GetDailyFormatted();
				
		try {
			getDailyToReturn.setCityName(dailies.getCityName());
			if(dailies.getDaily() != null) {
				for(Daily day : dailies.getDaily()) {
					getDailyToReturn.getDaily().add(convertToDailyToReturn(day));
				}
			}
		}catch(Exception e) {
			logger.error("Error in convertToGetDailyToReturn", e);
		}
		
		return getDailyToReturn;
	}
	
	/**
	 * convert Daily object to DailyFormatted
	 */
	private DailyFormatted convertToDailyToReturn(Daily daily) {
		DailyFormatted dailyToReturn = new DailyFormatted();
		
		try {
			dailyToReturn.setMaximum(daily.getTemp().getMax() + " °C");
			dailyToReturn.setFeelsLikeTemperature(daily.getFeels_like().getDay() + " °C");
			dailyToReturn.setHumidity(daily.getHumidity() + " %");
			dailyToReturn.setDateTime(daily.getDateTime());
		}catch(Exception e) {
			logger.error("Error in convertToDailyToReturn", e);
		}
		
		return dailyToReturn;
	}

}
