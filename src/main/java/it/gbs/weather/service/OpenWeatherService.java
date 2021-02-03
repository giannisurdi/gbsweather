package it.gbs.weather.service;

import it.gbs.weather.model.Coordinates;
import it.gbs.weather.model.GetDaily;
import it.gbs.weather.model.GetDailyFormatted;

public interface OpenWeatherService {
	
	GetDaily getNextTwoDayWeather(String cityName) throws Exception;
	
	Coordinates getCoordinates(String cityName) throws Exception;
	
	GetDailyFormatted getNextTwoDayWeatherFormatted(String cityName) throws Exception;

}
