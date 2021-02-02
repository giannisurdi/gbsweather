package it.gbs.weather.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.gbs.weather.model.Coordinates;
import it.gbs.weather.model.Daily;
import it.gbs.weather.model.DailyToReturn;
import it.gbs.weather.model.GetCurrent;
import it.gbs.weather.model.GetDaily;
import it.gbs.weather.model.GetDailyToReturn;
import it.gbs.weather.service.OpenWeatherService;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
	
	@Value( "${openweather.get.current.url}" )
	private String currentUrl;
	
	@Value( "${openweather.get.hourly.url}" )
	private String hourlyUrl;
	
	@Value( "${openweather.key}" )
	private String appId;

	@Override
	public GetDaily getNextTwoDayWeather(String cityName) throws Exception{
		Coordinates coordinates = getCoordinates(cityName);
		RestTemplate restTemplate = new RestTemplate();
		GetDaily daylies = restTemplate
				  .getForObject(hourlyUrl + "?lat=" + coordinates.getLat() + 
						  "&lon=" + coordinates.getLon() + 
						  "&exclude=minutely,hourly,current,alerts" + 
						  "&units=metric" + 
						  "&appid=" + appId, GetDaily.class);
		daylies.setCityName(cityName);
		return daylies;
	}

	@Override
	public Coordinates getCoordinates(String cityName) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		GetCurrent getCurrent = restTemplate
				  .getForObject(currentUrl + "?q=" + cityName + "&appid=" + appId, GetCurrent.class);
		return getCurrent.getCoord();
	}

	@Override
	public GetDailyToReturn getNextTwoDayWeatherFormatted(String cityName) throws Exception {
		Coordinates coordinates = getCoordinates(cityName);
		RestTemplate restTemplate = new RestTemplate();
		GetDaily daylies = restTemplate
				  .getForObject(hourlyUrl + "?lat=" + coordinates.getLat() + 
						  "&lon=" + coordinates.getLon() + 
						  "&exclude=minutely,hourly,current,alerts" + 
						  "&units=metric" + 
						  "&appid=" + appId, GetDaily.class);
		daylies.setCityName(cityName);
		return convertToGetDailyToReturn(daylies);
	}
	
	private GetDailyToReturn convertToGetDailyToReturn(GetDaily dailies) {
		GetDailyToReturn getDailyToReturn = new GetDailyToReturn();
				
		try {
			getDailyToReturn.setCityName(dailies.getCityName());
			if(dailies.getDaily() != null) {
				for(Daily day : dailies.getDaily()) {
					getDailyToReturn.getDaily().add(convertToDailyToReturn(day));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return getDailyToReturn;
	}
	
	private DailyToReturn convertToDailyToReturn(Daily daily) {
		DailyToReturn dailyToReturn = new DailyToReturn();
		
		try {
			dailyToReturn.setMaximum(daily.getTemp().getMax() + " °C");
			dailyToReturn.setFeelsLikeTemperature(daily.getFeels_like().getDay() + " °C");
			dailyToReturn.setHumidity(daily.getHumidity() + " %");
			dailyToReturn.setDateTime(daily.getDateTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dailyToReturn;
	}

}
