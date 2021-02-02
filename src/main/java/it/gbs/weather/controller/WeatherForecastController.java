package it.gbs.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.gbs.weather.model.GetDaily;
import it.gbs.weather.model.GetDailyToReturn;
import it.gbs.weather.service.OpenWeatherService;

@RestController
public class WeatherForecastController {
	
	@Autowired
	private OpenWeatherService openWeatherService;
	
	@RequestMapping("/getNextTwoDaysWeatherByCityName")
	@ResponseBody
	public GetDaily getNextTwoDaysWeatherByCityName(String cityName) throws Exception {
		try {
			return openWeatherService.getNextTwoDayWeather(cityName);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@RequestMapping("/getNextTwoDaysWeatherByCityNameFormatted")
	@ResponseBody
	public GetDailyToReturn getNextTwoDaysWeatherByCityNameFormatted(String cityName) throws Exception {
		try {
			return openWeatherService.getNextTwoDayWeatherFormatted(cityName);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
